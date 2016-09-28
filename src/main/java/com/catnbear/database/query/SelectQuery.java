package com.catnbear.database.query;

import com.catnbear.database.filter.FiltersList;

import java.util.List;

public class SelectQuery extends Query {

    private FiltersList filtersList;

    public SelectQuery(String databaseName, String tableName) {
        super(databaseName, tableName);
        filtersList = new FiltersList();
    }

    public SelectQuery(String databaseName, String tableName, List<String> columnsList) {
        super(databaseName, tableName, columnsList);
        filtersList = new FiltersList();
    }

    public SelectQuery(String databaseName, String tableName, FiltersList filtersList) {
        super(databaseName, tableName);
        this.filtersList = filtersList;
    }

    @Override
    public String toExecutableString() {
        String result = "SELECT ";

        if (!(columnsList.isEmpty() || (columnsList == null))) {
            for (String column : columnsList) {
                result += column;
                if (columnsList.indexOf(column) < (columnsList.size() - 1)) {
                    result += ",";
                }
            }
            result += " ";
        } else {
            result += "* ";
        }

        result += "FROM " + databaseName + "." + tableName + " ";

        if (!((filtersList.isEmpty()) || (filtersList == null))) {
            result += "WHERE ";
            result += filtersList.toQueryString();
        }

        result += ";";
        return result;
    }
}
