package com.example.springsecurity.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArrayTypeHandler extends BaseTypeHandler<Set<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType) {

    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return convertArrayToSet(resultSet.getArray(columnName));
    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return convertArrayToSet(resultSet.getArray(columnIndex));
    }

    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertArrayToSet(cs.getArray(columnIndex));
    }

    private Set<String> convertArrayToSet(Array array) throws SQLException {
        if (array == null) return new HashSet<>();
        
        String[] arrayValues = (String[]) array.getArray();
        Set<String> resultSet = new HashSet<>();
        Collections.addAll(resultSet, arrayValues);
        return resultSet;
    }
}
