package com.solution.allcups.reportbuilder.service;

import com.solution.allcups.reportbuilder.database.DatabaseData;
import com.solution.allcups.reportbuilder.database.DatabaseManager;
import com.solution.allcups.reportbuilder.entity.*;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.repository.ReportRepository;
import com.solution.allcups.reportbuilder.repository.ReportTableRepository;
import com.solution.allcups.reportbuilder.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private DatabaseData databaseData;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportTableRepository reportTableRepository;

    @Autowired
    private TableRepository tableRepository;

    public void createReport(Report report) throws NotAcceptableException {
        validateReport(report);

        setSize(report);

        reportRepository.save(report);

        for (ReportTable table : report.getTables()) {
            table.setReport(report);
        }

        reportTableRepository.saveAll(report.getTables());
    }

    public Report getReportById(Integer id) throws NotAcceptableException {
        Optional<Report> report = reportRepository.findById(id);

        if (report.isPresent()) {
            setSize(report.get());

            return report.get();
        }

        throw new NotAcceptableException();
    }

    private void validateReport(Report report) throws NotAcceptableException {
        if (reportRepository.existsById(report.getReportId())
                || report.getTableAmount() != report.getTables().size()
        ) {
            throw new NotAcceptableException();
        }

        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

        for (ReportTable reportTable : report.getTables()) {
            if (!isTablePresent(databaseManager, reportTable.getTableName())) {
                throw new NotAcceptableException();
            }

            if (!isColumnsValid(reportTable)) {
                throw new NotAcceptableException();
            }
        }

        databaseManager.close();
    }

    private void setSize(Report report) throws NotAcceptableException {
        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

        for (int i = 0; i < report.getTableAmount(); i++) {
            String size = null;

            String sql = "select count(*) from " + report.getTables().get(i).getTableName();

            try(ResultSet sizeRs = databaseManager.executeQuery(sql)) {
                if (sizeRs.first()) {
                    size = String.valueOf(sizeRs.getInt("COUNT(*)"));
                }
            } catch (SQLException e) {
                throw new NotAcceptableException();
            }

            String finalSize = size;
            report.getTables().get(i).getColumns().forEach(column -> {
                column.setSize(finalSize);
            });
        }

        databaseManager.close();
    }

    private boolean isColumnsValid(ReportTable reportTable) {
        Optional<Table> table = tableRepository.findById(reportTable.getTableName());

        if (table.isPresent()) {
            List<Column> columnList = table.get().getColumnInfos();
            List<ReportColumn> reportColumnList = reportTable.getColumns();

            columnList.sort(Comparator.comparingInt(Column::hashCode));
            reportColumnList.sort(Comparator.comparingInt(ReportColumn::hashCode));

            return columnList.toString().equals(reportColumnList.toString());
        }

        return true;
    }

    private boolean isTablePresent(DatabaseManager databaseManager, String name) throws NotAcceptableException {
        String sql = "show tables";

        boolean isExist = false;

        try(ResultSet tables = databaseManager.executeQuery(sql)) {
            while (tables.next()) {
                if (tables.getString("TABLE_NAME").equals(name.toUpperCase())) {
                    isExist = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }

        return isExist;
    }
}
