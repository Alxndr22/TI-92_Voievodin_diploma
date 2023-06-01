package Resources.Models;

import Resources.ConnectorDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static boolean create(boolean createDataBase) {
        Connection cn;
        Statement stat;
        String query;
        if (createDataBase) {
            try {
                cn = ConnectorDB.getConnectionWithoutDatabase();
                stat = cn.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
            query = "CREATE DATABASE `" + ConnectorDB.getDatabaseName() + "`;";
            try {
                stat.execute(query);
            } catch (SQLException e) {
                System.out.println("Error when creating database: " + e);
                return false;
            }
        }


        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        query = "CREATE TABLE `teachers`(`ID` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, `name` text DEFAULT NULL, `email` text DEFAULT NULL, `job title` text DEFAULT NULL, `bid` FLOAT DEFAULT NULL);";
        try {
            stat.execute(query);
        } catch (SQLException e) {
            System.out.println("Error when creating table 'teachers': " + e);
        }
        query = "CREATE TABLE `DiplomaStudentsData`(" +
                "`id` INT NOT NULL PRIMARY KEY," +
                "    `name` TEXT NOT NULL," +
                "`iz_mopp_b` INT NOT NULL," +
                "    `iz_mopp_k` INT NOT NULL," +
                "    `iz_monp_b` INT NOT NULL," +
                "    `iz_monp_k` INT NOT NULL," +
                "    `kp_p_b` INT NOT NULL," +
                "    `kp_p_k` INT NOT NULL," +
                "    `kp_pd_b` INT NOT NULL," +
                "    `kp_pd_k` INT NOT NULL," +
                "    `kp_nd_b` INT NOT NULL," +
                "    `kp_nd_k` INT NOT NULL," +
                "    `kar_b_b` INT NOT NULL," +
                "    `kar_b_k` INT NOT NULL," +
                "    `kar_b_b_z` INT NOT NULL," +
                "    `kar_b_k_z` INT NOT NULL," +
                "    `kar_mopp_b` INT NOT NULL," +
                "    `kar_mopp_k` INT NOT NULL," +
                "    `kar_mopp_b_z` INT NOT NULL," +
                "    `kar_mopp_k_z` INT NOT NULL," +
                "    `kar_monp_b` INT NOT NULL," +
                "    `kar_monp_k` INT NOT NULL," +
                "    `koar_b_b` INT NOT NULL," +
                "    `koar_b_k` INT NOT NULL," +
                "    `koar_mopp_b` INT NOT NULL," +
                "    `koar_mopp_k` INT NOT NULL," +
                "    `koar_monp_b` INT NOT NULL," +
                "    `koar_monp_k` INT NOT NULL," +
                "    `kove_b_b` INT NOT NULL," +
                "    `kove_b_k` INT NOT NULL," +
                "    `kove_mopp_b` INT NOT NULL," +
                "    `kove_mopp_k` INT NOT NULL," +
                "    `kove_monp_b` INT NOT NULL," +
                "    `kove_monp_k` INT NOT NULL," +
                "    `rar_b_b` INT NOT NULL," +
                "    `rar_b_k` INT NOT NULL," +
                "    `rar_mopp_b` INT NOT NULL," +
                "    `rar_mopp_k` INT NOT NULL," +
                "    `rar_monp_b` INT NOT NULL," +
                "    `rar_monp_k` INT NOT NULL," +
                "    `pvi_mopp_b` INT NOT NULL," +
                "    `pvi_mopp_k` INT NOT NULL," +
                "    `pvi_monp_b` INT NOT NULL," +
                "    `pvi_monp_k` INT NOT NULL," +
                "    `pvi_df_b` INT NOT NULL," +
                "    `pvi_df_k` INT NOT NULL," +
                "    `rve_zd_b_b` INT NOT NULL," +
                "    `rve_zd_b_k` INT NOT NULL," +
                "    `rve_ey_b_b` INT NOT NULL," +
                "    `rve_ey_b_k` INT NOT NULL," +
                "    `rve_ep_b_b` INT NOT NULL," +
                "    `rve_ep_b_k` INT NOT NULL," +
                "    `rve_zd_mopp_b` INT NOT NULL," +
                "    `rve_zd_mopp_k` INT NOT NULL," +
                "    `rve_e_mopp_b` INT NOT NULL," +
                "    `rve_e_mopp_k` INT NOT NULL," +
                "    `rve_zd_monp_b` INT NOT NULL," +
                "    `rve_zd_monp_k` INT NOT NULL," +
                "    `k_a_b` INT NOT NULL," +
                "    `k_a_k` INT NOT NULL," +
                "    `k_zdo_b` INT NOT NULL," +
                "    `k_zdo_k` INT NOT NULL," +
                "    `iz_mopp_b_2` INT NOT NULL," +
                "    `iz_mopp_k_2` INT NOT NULL," +
                "    `iz_monp_b_2` INT NOT NULL," +
                "    `iz_monp_k_2` INT NOT NULL," +
                "    `kp_p_b_2` INT NOT NULL," +
                "    `kp_p_k_2` INT NOT NULL," +
                "    `kp_pd_b_2` INT NOT NULL," +
                "    `kp_pd_k_2` INT NOT NULL," +
                "    `kp_nd_b_2` INT NOT NULL," +
                "    `kp_nd_k_2` INT NOT NULL," +
                "    `kar_b_b_2` INT NOT NULL," +
                "    `kar_b_k_2` INT NOT NULL," +
                "    `kar_b_b_z_2` INT NOT NULL," +
                "    `kar_b_k_z_2` INT NOT NULL," +
                "    `kar_mopp_b_2` INT NOT NULL," +
                "    `kar_mopp_k_2` INT NOT NULL," +
                "    `kar_mopp_b_z_2` INT NOT NULL," +
                "    `kar_mopp_k_z_2` INT NOT NULL," +
                "    `kar_monp_b_2` INT NOT NULL," +
                "    `kar_monp_k_2` INT NOT NULL," +
                "    `koar_b_b_2` INT NOT NULL," +
                "    `koar_b_k_2` INT NOT NULL," +
                "    `koar_mopp_b_2` INT NOT NULL," +
                "    `koar_mopp_k_2` INT NOT NULL," +
                "    `koar_monp_b_2` INT NOT NULL," +
                "    `koar_monp_k_2` INT NOT NULL," +
                "    `kove_b_b_2` INT NOT NULL," +
                "    `kove_b_k_2` INT NOT NULL," +
                "    `kove_mopp_b_2` INT NOT NULL," +
                "    `kove_mopp_k_2` INT NOT NULL," +
                "    `kove_monp_b_2` INT NOT NULL," +
                "    `kove_monp_k_2` INT NOT NULL," +
                "    `rar_b_b_2` INT NOT NULL," +
                "    `rar_b_k_2` INT NOT NULL," +
                "    `rar_mopp_b_2` INT NOT NULL," +
                "    `rar_mopp_k_2` INT NOT NULL," +
                "    `rar_monp_b_2` INT NOT NULL," +
                "    `rar_monp_k_2` INT NOT NULL," +
                "    `pvi_mopp_b_2` INT NOT NULL," +
                "    `pvi_mopp_k_2` INT NOT NULL," +
                "    `pvi_monp_b_2` INT NOT NULL," +
                "    `pvi_monp_k_2` INT NOT NULL," +
                "    `pvi_df_b_2` INT NOT NULL," +
                "    `pvi_df_k_2` INT NOT NULL," +
                "   " +
                "    `rve_zd_b_b_2` INT NOT NULL," +
                "    `rve_zd_b_k_2` INT NOT NULL," +
                "    `rve_ey_b_b_2` INT NOT NULL," +
                "    `rve_ey_b_k_2` INT NOT NULL," +
                "    `rve_ep_b_b_2` INT NOT NULL," +
                "    `rve_ep_b_k_2` INT NOT NULL," +
                "    `rve_zd_mopp_b_2` INT NOT NULL," +
                "    `rve_zd_mopp_k_2` INT NOT NULL," +
                "    `rve_e_mopp_b_2` INT NOT NULL," +
                "    `rve_e_mopp_k_2` INT NOT NULL," +
                "    `rve_zd_monp_b_2` INT NOT NULL," +
                "    `rve_zd_monp_k_2` INT NOT NULL," +
                "   " +
                "    `k_a_b_2` INT NOT NULL," +
                "    `k_a_k_2` INT NOT NULL," +
                "    `k_zdo_b_2` INT NOT NULL," +
                "    `k_zdo_k_2` INT NOT NULL" +
                ");";
        try {
            stat.execute(query);
        } catch (SQLException e) {
            System.out.println("Error when creating table 'DiplomaStudentsData': " + e);
        }
        query = "CREATE TABLE `NormInHours`(" +
                "`id` INT NOT NULL PRIMARY KEY," +
                "    `title` VARCHAR(35) NOT NULL," +
                "    `all` DOUBLE, " +
                "    `b` DOUBLE, " +
                "    `m_opp` DOUBLE, " +
                "    `m_onp` DOUBLE, " +
                "    `df` DOUBLE, " +
                "    `a` DOUBLE," +
                "    `a_z` DOUBLE," +
                "    `zd_st` DOUBLE" +
                ");";
        try {
            stat.execute(query);
        } catch (SQLException e) {
            System.out.println("Error when creating table 'NormInHours': " + e);
        }
        query = "CREATE TABLE `CourseGroups`(" +
                "`id` INT NOT NULL PRIMARY KEY," +
                "    `semester` CHAR NOT NULL," +
                "    `workType` VARCHAR(35) NOT NULL," +
                "    `subWorkType` VARCHAR(35) NOT NULL," +
                "    `studentsType` VARCHAR(35) NOT NULL," +
                "    `course` CHAR NOT NULL," +
                "    `groups` VARCHAR(60) NOT NULL" +
                ");";
        try {
            stat.execute(query);
        } catch (SQLException e) {
            System.out.println("Error when creating table 'CourseGroups': " + e);
        }
        query = "CREATE TABLE `report` (" +
                "  `id` bigint(20) NOT NULL PRIMARY KEY," +
                "  `0_25х_fх(б+бк)` float DEFAULT NULL," +
                "  `0_33х_dх(б+бк)` float DEFAULT NULL," +
                "  `0_33х_dх(к+кк)` float DEFAULT NULL," +
                "  `2х_ехгк+0,06х_nх(к+кк)/25` float DEFAULT NULL," +
                "  `0_33х_ex(б+бк)` float DEFAULT NULL," +
                "  `2 х e х г + 0,06 х n х (б+бк)/25` float DEFAULT NULL," +
                "  `0_33х_ex(к+кк)` float DEFAULT NULL," +
                "  `2х_гхz` float DEFAULT NULL," +
                "  `gх(б+бк)` float DEFAULT NULL," +
                "  `gх(к+кк)` float DEFAULT NULL," +
                "  `іх_гх0,25` float DEFAULT NULL," +
                "  `іх_гх0,26` float DEFAULT NULL," +
                "  `0_25х_fх(к+кк)` float DEFAULT NULL," +
                "  `л х р` float DEFAULT NULL," +
                "  `л х рк` float DEFAULT NULL," +
                "  `l х гкl` float DEFAULT NULL," +
                "  `l х гl` float DEFAULT NULL," +
                "  `0_25х_мх(б+бк)` float DEFAULT NULL," +
                "  `0_25х_мх(к+кк)` float DEFAULT NULL," +
                "  `n` float DEFAULT NULL," +
                "  `п х гкп` float DEFAULT NULL," +
                "  `п х гп` float DEFAULT NULL," +
                "  `qх(б+бк)` float DEFAULT NULL," +
                "  `qх(к+кк)` float DEFAULT NULL," +
                "  `0_5х_rх(б+бк)` float DEFAULT NULL," +
                "  `0_5х_rх(к+кк)` float DEFAULT NULL," +
                "  `всього годин` float DEFAULT NULL," +
                "  `всього годин 2` float DEFAULT NULL," +
                "  `заліки,+al10` float DEFAULT NULL," +
                "  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
        try {
            stat.execute(query);
        } catch (SQLException e) {
            System.out.println("Error when creating table 'report': " + e);
        }
        System.out.println("-- Successfully created tables --");

        return true;
    }

}
