package com.org.security.config;

import com.org.security.exception.ActorException;
import com.org.security.exception.ErrorCode;
import com.org.security.exception.ErrorSeverity;
import com.org.security.utils.ActorConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfig {
    @Bean
    DataSource dataSource() {
        String dbUser = ActorConstant.DB_USER_NAME;
        String dbPassword = ActorConstant.DB_PASSWORD;
        String driverClassName = ActorConstant.DB_DRIVER_CLASS_NAME;
        DriverManagerDataSource ds = new DriverManagerDataSource(getDBUrl(), dbUser, dbPassword);
        try {
            ds.setDriverClassName(driverClassName);
        } catch (Exception e) {
            throw new ActorException(ErrorCode.ERR002.getErrorCode(), ErrorSeverity.FATAL,
                    ErrorCode.ERR002.getErrorMessage(), e);
        }
        try {
            ds.getConnection().close();
        } catch (SQLException e) {
            throw new ActorException(ErrorCode.ERR002.getErrorCode(), ErrorSeverity.FATAL,
                    ErrorCode.ERR002.getErrorMessage(), e);
        }
        return ds;
    }

    private String getDBUrl() {
        String dbHost = ActorConstant.DB_HOST;
        String dbPort = ActorConstant.DB_PORT;
        String dbName = ActorConstant.DB_NAME;
        String dbUrlPrefix = ActorConstant.DB_URL_PREFIX;
        StringBuilder baseUrl = new StringBuilder(dbUrlPrefix);
        baseUrl.append(dbHost);
        baseUrl.append(ActorConstant.COLON);
        baseUrl.append(dbPort);
//		baseUrl.append(EMPConstant.COLON);
        baseUrl.append(dbName);
        return baseUrl.toString();
    }
}
