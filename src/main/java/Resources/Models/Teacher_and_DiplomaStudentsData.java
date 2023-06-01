package Resources.Models;

import Resources.ConnectorDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Teacher_and_DiplomaStudentsData {

    // all needed columns of table 'DiplomaStudentsData'
    static String[] columnNames = {"iz_mopp_b", "iz_mopp_k", "iz_monp_b",
            "iz_monp_k", "kp_p_b", "kp_p_k", "kp_pd_b", "kp_pd_k", "kp_nd_b", "kp_nd_k", "kar_b_b", "kar_b_k", "kar_b_b_z",
            "kar_b_k_z", "kar_mopp_b", "kar_mopp_k", "kar_mopp_b_z", "kar_mopp_k_z",
            "kar_monp_b", "kar_monp_k", "koar_b_b", "koar_b_k", "koar_mopp_b", "koar_mopp_k", "koar_monp_b",
            "koar_monp_k", "kove_b_b", "kove_b_k", "kove_mopp_b", "kove_mopp_k", "kove_monp_b", "kove_monp_k",
            "rar_b_b", "rar_b_k", "rar_mopp_b", "rar_mopp_k", "rar_monp_b", "rar_monp_k", "pvi_mopp_b", "pvi_mopp_k",
            "pvi_monp_b", "pvi_monp_k", "pvi_df_b", "pvi_df_k", "rve_zd_b_b", "rve_zd_b_k", "rve_ey_b_b", "rve_ey_b_k",
            "rve_ep_b_b", "rve_ep_b_k", "rve_zd_mopp_b", "rve_zd_mopp_k", "rve_e_mopp_b", "rve_e_mopp_k", "rve_zd_monp_b",
            "rve_zd_monp_k", "k_a_b", "k_a_k", "k_zdo_b", "k_zdo_k",
            "iz_mopp_b_2", "iz_mopp_k_2", "iz_monp_b_2",
            "iz_monp_k_2", "kp_p_b_2", "kp_p_k_2", "kp_pd_b_2", "kp_pd_k_2", "kp_nd_b_2", "kp_nd_k_2", "kar_b_b_2", "kar_b_k_2", "kar_b_b_z_2",
            "kar_b_k_z_2", "kar_mopp_b_2", "kar_mopp_k_2", "kar_mopp_b_z_2", "kar_mopp_k_z_2",
            "kar_monp_b_2", "kar_monp_k_2", "koar_b_b_2", "koar_b_k_2", "koar_mopp_b_2", "koar_mopp_k_2", "koar_monp_b_2",
            "koar_monp_k_2", "kove_b_b_2", "kove_b_k_2", "kove_mopp_b_2", "kove_mopp_k_2", "kove_monp_b_2", "kove_monp_k_2",
            "rar_b_b_2", "rar_b_k_2", "rar_mopp_b_2", "rar_mopp_k_2", "rar_monp_b_2", "rar_monp_k_2", "pvi_mopp_b_2", "pvi_mopp_k_2",
            "pvi_monp_b_2", "pvi_monp_k_2", "pvi_df_b_2", "pvi_df_k_2", "rve_zd_b_b_2", "rve_zd_b_k_2", "rve_ey_b_b_2", "rve_ey_b_k_2",
            "rve_ep_b_b_2", "rve_ep_b_k_2", "rve_zd_mopp_b_2", "rve_zd_mopp_k_2", "rve_e_mopp_b_2", "rve_e_mopp_k_2", "rve_zd_monp_b_2",
            "rve_zd_monp_k_2", "k_a_b_2", "k_a_k_2", "k_zdo_b_2", "k_zdo_k_2"};

    private int id_teacher;      // id teacher
    private String name;
    private String job_title;

    private int iz_mopp_b;
    private int iz_mopp_k;
    private int iz_monp_b;
    private int iz_monp_k;
    private int kp_p_b;
    private int kp_p_k;
    private int kp_pd_b;
    private int kp_pd_k;
    private int kp_nd_b;
    private int kp_nd_k;
    private int kar_b_b;
    private int kar_b_k;
    private int kar_b_b_z;
    private int kar_b_k_z;
    private int kar_mopp_b;
    private int kar_mopp_k;
    private int kar_mopp_b_z;
    private int kar_mopp_k_z;
    private int kar_monp_b;
    private int kar_monp_k;
    private int koar_b_b;
    private int koar_b_k;
    private int koar_mopp_b;
    private int koar_mopp_k;
    private int koar_monp_b;
    private int koar_monp_k;
    private int kove_b_b;
    private int kove_b_k;
    private int kove_mopp_b;
    private int kove_mopp_k;
    private int kove_monp_b;
    private int kove_monp_k;
    private int rar_b_b;
    private int rar_b_k;
    private int rar_mopp_b;
    private int rar_mopp_k;
    private int rar_monp_b;
    private int rar_monp_k;
    private int pvi_mopp_b;
    private int pvi_mopp_k;
    private int pvi_monp_b;
    private int pvi_monp_k;
    private int pvi_df_b;
    private int pvi_df_k;
    private int rve_zd_b_b, rve_zd_b_k, rve_ey_b_b, rve_ey_b_k, rve_ep_b_b, rve_ep_b_k, rve_zd_mopp_b, rve_zd_mopp_k, rve_e_mopp_b, rve_e_mopp_k, rve_zd_monp_b, rve_zd_monp_k;
    private int k_a_b, k_a_k, k_zdo_b, k_zdo_k;

    private int iz_mopp_b_2;
    private int iz_mopp_k_2;
    private int iz_monp_b_2;
    private int iz_monp_k_2;
    private int kp_p_b_2;
    private int kp_p_k_2;
    private int kp_pd_b_2;
    private int kp_pd_k_2;
    private int kp_nd_b_2;
    private int kp_nd_k_2;
    private int kar_b_b_2;
    private int kar_b_k_2;
    private int kar_b_b_z_2;
    private int kar_b_k_z_2;
    private int kar_mopp_b_2;
    private int kar_mopp_k_2;
    private int kar_mopp_b_z_2;
    private int kar_mopp_k_z_2;
    private int kar_monp_b_2;
    private int kar_monp_k_2;
    private int koar_b_b_2;
    private int koar_b_k_2;
    private int koar_mopp_b_2;
    private int koar_mopp_k_2;
    private int koar_monp_b_2;
    private int koar_monp_k_2;
    private int kove_b_b_2;
    private int kove_b_k_2;
    private int kove_mopp_b_2;
    private int kove_mopp_k_2;
    private int kove_monp_b_2;
    private int kove_monp_k_2;
    private int rar_b_b_2;
    private int rar_b_k_2;
    private int rar_mopp_b_2;
    private int rar_mopp_k_2;
    private int rar_monp_b_2;
    private int rar_monp_k_2;
    private int pvi_mopp_b_2;
    private int pvi_mopp_k_2;
    private int pvi_monp_b_2;
    private int pvi_monp_k_2;
    private int pvi_df_b_2;
    private int pvi_df_k_2;
    private int rve_zd_b_b_2, rve_zd_b_k_2, rve_ey_b_b_2, rve_ey_b_k_2, rve_ep_b_b_2, rve_ep_b_k_2, rve_zd_mopp_b_2, rve_zd_mopp_k_2, rve_e_mopp_b_2, rve_e_mopp_k_2, rve_zd_monp_b_2, rve_zd_monp_k_2;
    private int k_a_b_2, k_a_k_2, k_zdo_b_2, k_zdo_k_2;

    public Teacher_and_DiplomaStudentsData(int id_teacher, String name, String job_title, int iz_mopp_b, int iz_mopp_k, int iz_monp_b, int iz_monp_k, int kp_p_b, int kp_p_k, int kp_pd_b, int kp_pd_k, int kp_nd_b, int kp_nd_k, int kar_b_b, int kar_b_k, int kar_b_b_z, int kar_b_k_z, int kar_mopp_b, int kar_mopp_k, int kar_mopp_b_z, int kar_mopp_k_z, int kar_monp_b, int kar_monp_k, int koar_b_b, int koar_b_k, int koar_mopp_b, int koar_mopp_k, int koar_monp_b, int koar_monp_k, int kove_b_b, int kove_b_k, int kove_mopp_b, int kove_mopp_k, int kove_monp_b, int kove_monp_k, int rar_b_b, int rar_b_k, int rar_mopp_b, int rar_mopp_k, int rar_monp_b, int rar_monp_k, int pvi_mopp_b, int pvi_mopp_k, int pvi_monp_b, int pvi_monp_k, int pvi_df_b, int pvi_df_k, int rve_zd_b_b, int rve_zd_b_k, int rve_ey_b_b, int rve_ey_b_k, int rve_ep_b_b, int rve_ep_b_k, int rve_zd_mopp_b, int rve_zd_mopp_k, int rve_e_mopp_b, int rve_e_mopp_k, int rve_zd_monp_b, int rve_zd_monp_k, int k_a_b, int k_a_k, int k_zdo_b, int k_zdo_k, int iz_mopp_b_2, int iz_mopp_k_2, int iz_monp_b_2, int iz_monp_k_2, int kp_p_b_2, int kp_p_k_2, int kp_pd_b_2, int kp_pd_k_2, int kp_nd_b_2, int kp_nd_k_2, int kar_b_b_2, int kar_b_k_2, int kar_b_b_z_2, int kar_b_k_z_2, int kar_mopp_b_2, int kar_mopp_k_2, int kar_mopp_b_z_2, int kar_mopp_k_z_2, int kar_monp_b_2, int kar_monp_k_2, int koar_b_b_2, int koar_b_k_2, int koar_mopp_b_2, int koar_mopp_k_2, int koar_monp_b_2, int koar_monp_k_2, int kove_b_b_2, int kove_b_k_2, int kove_mopp_b_2, int kove_mopp_k_2, int kove_monp_b_2, int kove_monp_k_2, int rar_b_b_2, int rar_b_k_2, int rar_mopp_b_2, int rar_mopp_k_2, int rar_monp_b_2, int rar_monp_k_2, int pvi_mopp_b_2, int pvi_mopp_k_2, int pvi_monp_b_2, int pvi_monp_k_2, int pvi_df_b_2, int pvi_df_k_2, int rve_zd_b_b_2, int rve_zd_b_k_2, int rve_ey_b_b_2, int rve_ey_b_k_2, int rve_ep_b_b_2, int rve_ep_b_k_2, int rve_zd_mopp_b_2, int rve_zd_mopp_k_2, int rve_e_mopp_b_2, int rve_e_mopp_k_2, int rve_zd_monp_b_2, int rve_zd_monp_k_2, int k_a_b_2, int k_a_k_2, int k_zdo_b_2, int k_zdo_k_2) {
        this.id_teacher = id_teacher;
        this.name = name;
        this.job_title = job_title;
        this.iz_mopp_b = iz_mopp_b;
        this.iz_mopp_k = iz_mopp_k;
        this.iz_monp_b = iz_monp_b;
        this.iz_monp_k = iz_monp_k;
        this.kp_p_b = kp_p_b;
        this.kp_p_k = kp_p_k;
        this.kp_pd_b = kp_pd_b;
        this.kp_pd_k = kp_pd_k;
        this.kp_nd_b = kp_nd_b;
        this.kp_nd_k = kp_nd_k;
        this.kar_b_b = kar_b_b;
        this.kar_b_k = kar_b_k;
        this.kar_b_b_z = kar_b_b_z;
        this.kar_b_k_z = kar_b_k_z;
        this.kar_mopp_b = kar_mopp_b;
        this.kar_mopp_k = kar_mopp_k;
        this.kar_mopp_b_z = kar_mopp_b_z;
        this.kar_mopp_k_z = kar_mopp_k_z;
        this.kar_monp_b = kar_monp_b;
        this.kar_monp_k = kar_monp_k;
        this.koar_b_b = koar_b_b;
        this.koar_b_k = koar_b_k;
        this.koar_mopp_b = koar_mopp_b;
        this.koar_mopp_k = koar_mopp_k;
        this.koar_monp_b = koar_monp_b;
        this.koar_monp_k = koar_monp_k;
        this.kove_b_b = kove_b_b;
        this.kove_b_k = kove_b_k;
        this.kove_mopp_b = kove_mopp_b;
        this.kove_mopp_k = kove_mopp_k;
        this.kove_monp_b = kove_monp_b;
        this.kove_monp_k = kove_monp_k;
        this.rar_b_b = rar_b_b;
        this.rar_b_k = rar_b_k;
        this.rar_mopp_b = rar_mopp_b;
        this.rar_mopp_k = rar_mopp_k;
        this.rar_monp_b = rar_monp_b;
        this.rar_monp_k = rar_monp_k;
        this.pvi_mopp_b = pvi_mopp_b;
        this.pvi_mopp_k = pvi_mopp_k;
        this.pvi_monp_b = pvi_monp_b;
        this.pvi_monp_k = pvi_monp_k;
        this.pvi_df_b = pvi_df_b;
        this.pvi_df_k = pvi_df_k;
        this.rve_zd_b_b = rve_zd_b_b;
        this.rve_zd_b_k = rve_zd_b_k;
        this.rve_ey_b_b = rve_ey_b_b;
        this.rve_ey_b_k = rve_ey_b_k;
        this.rve_ep_b_b = rve_ep_b_b;
        this.rve_ep_b_k = rve_ep_b_k;
        this.rve_zd_mopp_b = rve_zd_mopp_b;
        this.rve_zd_mopp_k = rve_zd_mopp_k;
        this.rve_e_mopp_b = rve_e_mopp_b;
        this.rve_e_mopp_k = rve_e_mopp_k;
        this.rve_zd_monp_b = rve_zd_monp_b;
        this.rve_zd_monp_k = rve_zd_monp_k;
        this.k_a_b = k_a_b;
        this.k_a_k = k_a_k;
        this.k_zdo_b = k_zdo_b;
        this.k_zdo_k = k_zdo_k;
        this.iz_mopp_b_2 = iz_mopp_b_2;
        this.iz_mopp_k_2 = iz_mopp_k_2;
        this.iz_monp_b_2 = iz_monp_b_2;
        this.iz_monp_k_2 = iz_monp_k_2;
        this.kp_p_b_2 = kp_p_b_2;
        this.kp_p_k_2 = kp_p_k_2;
        this.kp_pd_b_2 = kp_pd_b_2;
        this.kp_pd_k_2 = kp_pd_k_2;
        this.kp_nd_b_2 = kp_nd_b_2;
        this.kp_nd_k_2 = kp_nd_k_2;
        this.kar_b_b_2 = kar_b_b_2;
        this.kar_b_k_2 = kar_b_k_2;
        this.kar_b_b_z_2 = kar_b_b_z_2;
        this.kar_b_k_z_2 = kar_b_k_z_2;
        this.kar_mopp_b_2 = kar_mopp_b_2;
        this.kar_mopp_k_2 = kar_mopp_k_2;
        this.kar_mopp_b_z_2 = kar_mopp_b_z_2;
        this.kar_mopp_k_z_2 = kar_mopp_k_z_2;
        this.kar_monp_b_2 = kar_monp_b_2;
        this.kar_monp_k_2 = kar_monp_k_2;
        this.koar_b_b_2 = koar_b_b_2;
        this.koar_b_k_2 = koar_b_k_2;
        this.koar_mopp_b_2 = koar_mopp_b_2;
        this.koar_mopp_k_2 = koar_mopp_k_2;
        this.koar_monp_b_2 = koar_monp_b_2;
        this.koar_monp_k_2 = koar_monp_k_2;
        this.kove_b_b_2 = kove_b_b_2;
        this.kove_b_k_2 = kove_b_k_2;
        this.kove_mopp_b_2 = kove_mopp_b_2;
        this.kove_mopp_k_2 = kove_mopp_k_2;
        this.kove_monp_b_2 = kove_monp_b_2;
        this.kove_monp_k_2 = kove_monp_k_2;
        this.rar_b_b_2 = rar_b_b_2;
        this.rar_b_k_2 = rar_b_k_2;
        this.rar_mopp_b_2 = rar_mopp_b_2;
        this.rar_mopp_k_2 = rar_mopp_k_2;
        this.rar_monp_b_2 = rar_monp_b_2;
        this.rar_monp_k_2 = rar_monp_k_2;
        this.pvi_mopp_b_2 = pvi_mopp_b_2;
        this.pvi_mopp_k_2 = pvi_mopp_k_2;
        this.pvi_monp_b_2 = pvi_monp_b_2;
        this.pvi_monp_k_2 = pvi_monp_k_2;
        this.pvi_df_b_2 = pvi_df_b_2;
        this.pvi_df_k_2 = pvi_df_k_2;
        this.rve_zd_b_b_2 = rve_zd_b_b_2;
        this.rve_zd_b_k_2 = rve_zd_b_k_2;
        this.rve_ey_b_b_2 = rve_ey_b_b_2;
        this.rve_ey_b_k_2 = rve_ey_b_k_2;
        this.rve_ep_b_b_2 = rve_ep_b_b_2;
        this.rve_ep_b_k_2 = rve_ep_b_k_2;
        this.rve_zd_mopp_b_2 = rve_zd_mopp_b_2;
        this.rve_zd_mopp_k_2 = rve_zd_mopp_k_2;
        this.rve_e_mopp_b_2 = rve_e_mopp_b_2;
        this.rve_e_mopp_k_2 = rve_e_mopp_k_2;
        this.rve_zd_monp_b_2 = rve_zd_monp_b_2;
        this.rve_zd_monp_k_2 = rve_zd_monp_k_2;
        this.k_a_b_2 = k_a_b_2;
        this.k_a_k_2 = k_a_k_2;
        this.k_zdo_b_2 = k_zdo_b_2;
        this.k_zdo_k_2 = k_zdo_k_2;
    }

    public int getId_teacher() {        return id_teacher;    }

    public String getName() {
        return name;    }

    public String getJob_title() {        return job_title;    }

    public int getIz_mopp_b() {        return iz_mopp_b;    }

    public int getIz_mopp_k() {        return iz_mopp_k;    }

    public int getIz_monp_b() {        return iz_monp_b;    }

    public int getIz_monp_k() {        return iz_monp_k;    }

    public int getKp_p_b() {        return kp_p_b;    }

    public int getKp_p_k() {        return kp_p_k;    }

    public int getKp_pd_b() {        return kp_pd_b;    }

    public int getKp_pd_k() {        return kp_pd_k;    }

    public int getKp_nd_b() {        return kp_nd_b;    }

    public int getKp_nd_k() {        return kp_nd_k;    }

    public int getKar_b_b() {        return kar_b_b;    }

    public int getKar_b_k() {        return kar_b_k;    }

    public int getKar_b_b_z() {        return kar_b_b_z;    }

    public int getKar_b_k_z() {        return kar_b_k_z;    }

    public int getKar_mopp_b() {        return kar_mopp_b;    }

    public int getKar_mopp_k() {        return kar_mopp_k;    }

    public int getKar_mopp_b_z() {        return kar_mopp_b_z;    }

    public int getKar_mopp_k_z() {        return kar_mopp_k_z;    }

    public int getKar_monp_b() {        return kar_monp_b;    }

    public int getKar_monp_k() {        return kar_monp_k;    }

    public int getKoar_b_b() {        return koar_b_b;    }

    public int getKoar_b_k() {        return koar_b_k;    }

    public int getKoar_mopp_b() {        return koar_mopp_b;    }

    public int getKoar_mopp_k() {        return koar_mopp_k;    }

    public int getKoar_monp_b() {        return koar_monp_b;    }

    public int getKoar_monp_k() {        return koar_monp_k;    }

    public int getKove_b_b() {        return kove_b_b;    }

    public int getKove_b_k() {        return kove_b_k;    }

    public int getKove_mopp_b() {        return kove_mopp_b;    }

    public int getKove_mopp_k() {        return kove_mopp_k;    }

    public int getKove_monp_b() {        return kove_monp_b;    }

    public int getKove_monp_k() {        return kove_monp_k;    }

    public int getRar_b_b() {        return rar_b_b;    }

    public int getRar_b_k() {        return rar_b_k;    }

    public int getRar_mopp_b() {        return rar_mopp_b;    }

    public int getRar_mopp_k() {        return rar_mopp_k;    }

    public int getRar_monp_b() {        return rar_monp_b;    }

    public int getRar_monp_k() {        return rar_monp_k;    }

    public int getPvi_mopp_b() {        return pvi_mopp_b;    }

    public int getPvi_mopp_k() {        return pvi_mopp_k;    }

    public int getPvi_monp_b() {        return pvi_monp_b;    }

    public int getPvi_monp_k() {        return pvi_monp_k;    }

    public int getPvi_df_b() {        return pvi_df_b;    }

    public int getPvi_df_k() {        return pvi_df_k;    }

    public int getRve_zd_b_b() {        return rve_zd_b_b;    }

    public int getRve_zd_b_k() {        return rve_zd_b_k;    }

    public int getRve_ey_b_b() {        return rve_ey_b_b;    }

    public int getRve_ey_b_k() {        return rve_ey_b_k;    }

    public int getRve_ep_b_b() {        return rve_ep_b_b;    }

    public int getRve_ep_b_k() {        return rve_ep_b_k;    }

    public int getRve_zd_mopp_b() {        return rve_zd_mopp_b;    }

    public int getRve_zd_mopp_k() {        return rve_zd_mopp_k;    }

    public int getRve_e_mopp_b() {        return rve_e_mopp_b;    }

    public int getRve_e_mopp_k() {        return rve_e_mopp_k;    }

    public int getRve_zd_monp_b() {        return rve_zd_monp_b;    }

    public int getRve_zd_monp_k() {        return rve_zd_monp_k;    }

    public int getK_a_b() {        return k_a_b;    }

    public int getK_a_k() {        return k_a_k;    }

    public int getK_zdo_b() {        return k_zdo_b;    }

    public int getK_zdo_k() {        return k_zdo_k;    }

    public int getIz_mopp_b_2() {        return iz_mopp_b_2;    }

    public int getIz_mopp_k_2() {        return iz_mopp_k_2;    }

    public int getIz_monp_b_2() {        return iz_monp_b_2;    }

    public int getIz_monp_k_2() {        return iz_monp_k_2;    }

    public int getKp_p_b_2() {        return kp_p_b_2;    }

    public int getKp_p_k_2() {        return kp_p_k_2;    }

    public int getKp_pd_b_2() {        return kp_pd_b_2;    }

    public int getKp_pd_k_2() {        return kp_pd_k_2;    }

    public int getKp_nd_b_2() {        return kp_nd_b_2;    }

    public int getKp_nd_k_2() {        return kp_nd_k_2;    }

    public int getKar_b_b_2() {        return kar_b_b_2;    }

    public int getKar_b_k_2() {        return kar_b_k_2;    }

    public int getKar_b_b_z_2() {        return kar_b_b_z_2;    }

    public int getKar_b_k_z_2() {        return kar_b_k_z_2;    }

    public int getKar_mopp_b_2() {        return kar_mopp_b_2;    }

    public int getKar_mopp_k_2() {        return kar_mopp_k_2;    }

    public int getKar_mopp_b_z_2() {        return kar_mopp_b_z_2;    }

    public int getKar_mopp_k_z_2() {        return kar_mopp_k_z_2;    }

    public int getKar_monp_b_2() {        return kar_monp_b_2;    }

    public int getKar_monp_k_2() {        return kar_monp_k_2;    }

    public int getKoar_b_b_2() {        return koar_b_b_2;    }

    public int getKoar_b_k_2() {        return koar_b_k_2;    }

    public int getKoar_mopp_b_2() {        return koar_mopp_b_2;    }

    public int getKoar_mopp_k_2() {        return koar_mopp_k_2;    }

    public int getKoar_monp_b_2() {        return koar_monp_b_2;    }

    public int getKoar_monp_k_2() {        return koar_monp_k_2;    }

    public int getKove_b_b_2() {        return kove_b_b_2;    }

    public int getKove_b_k_2() {        return kove_b_k_2;    }

    public int getKove_mopp_b_2() {        return kove_mopp_b_2;    }

    public int getKove_mopp_k_2() {        return kove_mopp_k_2;    }

    public int getKove_monp_b_2() {        return kove_monp_b_2;    }

    public int getKove_monp_k_2() {        return kove_monp_k_2;    }

    public int getRar_b_b_2() {        return rar_b_b_2;    }

    public int getRar_b_k_2() {        return rar_b_k_2;    }

    public int getRar_mopp_b_2() {        return rar_mopp_b_2;    }

    public int getRar_mopp_k_2() {        return rar_mopp_k_2;    }

    public int getRar_monp_b_2() {        return rar_monp_b_2;    }

    public int getRar_monp_k_2() {        return rar_monp_k_2;    }

    public int getPvi_mopp_b_2() {        return pvi_mopp_b_2;    }

    public int getPvi_mopp_k_2() {        return pvi_mopp_k_2;    }

    public int getPvi_monp_b_2() {        return pvi_monp_b_2;    }

    public int getPvi_monp_k_2() {        return pvi_monp_k_2;    }

    public int getPvi_df_b_2() {        return pvi_df_b_2;    }

    public int getPvi_df_k_2() {        return pvi_df_k_2;    }

    public int getRve_zd_b_b_2() {        return rve_zd_b_b_2;    }

    public int getRve_zd_b_k_2() {        return rve_zd_b_k_2;    }

    public int getRve_ey_b_b_2() {        return rve_ey_b_b_2;    }

    public int getRve_ey_b_k_2() {        return rve_ey_b_k_2;    }

    public int getRve_ep_b_b_2() {        return rve_ep_b_b_2;    }

    public int getRve_ep_b_k_2() {        return rve_ep_b_k_2;    }

    public int getRve_zd_mopp_b_2() {        return rve_zd_mopp_b_2;    }

    public int getRve_zd_mopp_k_2() {        return rve_zd_mopp_k_2;    }

    public int getRve_e_mopp_b_2() {        return rve_e_mopp_b_2;    }

    public int getRve_e_mopp_k_2() {        return rve_e_mopp_k_2;    }

    public int getRve_zd_monp_b_2() {        return rve_zd_monp_b_2;    }

    public int getRve_zd_monp_k_2() {        return rve_zd_monp_k_2;    }

    public int getK_a_b_2() {        return k_a_b_2;    }

    public int getK_a_k_2() {        return k_a_k_2;    }

    public int getK_zdo_b_2() {        return k_zdo_b_2;    }

    public int getK_zdo_k_2() {        return k_zdo_k_2; }

    static public void testDiplomaStudentsDataExistence() throws SQLException {
        ConnectorDB.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM `DiplomaStudentsData`;");
    }

    static public ArrayList<Teacher_and_DiplomaStudentsData> selectFromTeacher_and_DiplomaStudentsData() {
        Connection cn;
        Statement stat;
        ResultSet result;
        ArrayList<Teacher_and_DiplomaStudentsData> data = new ArrayList<>();

        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "SELECT `teachers`.`ID`, `teachers`.`name`, `teachers`.`job title`";
            for (int i = 0; i < columnNames.length; ++i)
                query += ", `DiplomaStudentsData`.`" + columnNames[i] + "`";
            query += " FROM `teachers`, `DiplomaStudentsData` WHERE `teachers`.`ID` = `DiplomaStudentsData`.`id`;";
            result = stat.executeQuery(query);
            while (result.next()) {
                data.add(new Teacher_and_DiplomaStudentsData(
                        result.getInt(1), result.getString(2), result.getString(3),
                        result.getInt(columnNames[0]),result.getInt(columnNames[1]),result.getInt(columnNames[2]), result.getInt(columnNames[3]), result.getInt(columnNames[4]), result.getInt(columnNames[5]),
                        result.getInt(columnNames[6]),result.getInt(columnNames[7]),result.getInt(columnNames[8]), result.getInt(columnNames[9]), result.getInt(columnNames[10]), result.getInt(columnNames[11]),
                        result.getInt(columnNames[12]),result.getInt(columnNames[13]),result.getInt(columnNames[14]), result.getInt(columnNames[15]), result.getInt(columnNames[16]), result.getInt(columnNames[17]),
                        result.getInt(columnNames[18]),result.getInt(columnNames[19]), result.getInt(columnNames[20]),result.getInt(columnNames[21]), result.getInt(columnNames[22]), result.getInt(columnNames[23]),
                        result.getInt(columnNames[24]), result.getInt(columnNames[25]), result.getInt(columnNames[26]), result.getInt(columnNames[27]), result.getInt(columnNames[28]), result.getInt(columnNames[29]),
                        result.getInt(columnNames[30]), result.getInt(columnNames[31]), result.getInt(columnNames[32]), result.getInt(columnNames[33]), result.getInt(columnNames[34]), result.getInt(columnNames[35]),
                        result.getInt(columnNames[36]), result.getInt(columnNames[37]), result.getInt(columnNames[38]), result.getInt(columnNames[39]), result.getInt(columnNames[40]), result.getInt(columnNames[41]),
                        result.getInt(columnNames[42]), result.getInt(columnNames[43]), result.getInt(columnNames[44]), result.getInt(columnNames[45]), result.getInt(columnNames[46]), result.getInt(columnNames[47]),
                        result.getInt(columnNames[48]), result.getInt(columnNames[49]), result.getInt(columnNames[50]), result.getInt(columnNames[51]), result.getInt(columnNames[52]), result.getInt(columnNames[53]),
                        result.getInt(columnNames[54]), result.getInt(columnNames[55]), result.getInt(columnNames[56]), result.getInt(columnNames[57]),
                        result.getInt(columnNames[58]), result.getInt(columnNames[59]),
                        result.getInt(columnNames[60]),result.getInt(columnNames[61]),result.getInt(columnNames[62]), result.getInt(columnNames[63]), result.getInt(columnNames[64]), result.getInt(columnNames[65]), result.getInt(columnNames[66]),result.getInt(columnNames[67]), result.getInt(columnNames[68]), result.getInt(columnNames[69]),
                        result.getInt(columnNames[70]),result.getInt(columnNames[71]),result.getInt(columnNames[72]), result.getInt(columnNames[73]), result.getInt(columnNames[74]), result.getInt(columnNames[75]), result.getInt(columnNames[76]),result.getInt(columnNames[77]), result.getInt(columnNames[78]), result.getInt(columnNames[79]),
                        result.getInt(columnNames[80]),result.getInt(columnNames[81]),result.getInt(columnNames[82]), result.getInt(columnNames[83]), result.getInt(columnNames[84]), result.getInt(columnNames[85]), result.getInt(columnNames[86]),result.getInt(columnNames[87]), result.getInt(columnNames[88]), result.getInt(columnNames[89]),
                        result.getInt(columnNames[90]),result.getInt(columnNames[91]),result.getInt(columnNames[92]), result.getInt(columnNames[93]), result.getInt(columnNames[94]), result.getInt(columnNames[95]), result.getInt(columnNames[96]),result.getInt(columnNames[97]), result.getInt(columnNames[98]), result.getInt(columnNames[99]),
                        result.getInt(columnNames[100]),result.getInt(columnNames[101]),result.getInt(columnNames[102]), result.getInt(columnNames[103]), result.getInt(columnNames[104]), result.getInt(columnNames[105]), result.getInt(columnNames[106]),result.getInt(columnNames[107]), result.getInt(columnNames[108]), result.getInt(columnNames[109]),
                        result.getInt(columnNames[110]),result.getInt(columnNames[111]),result.getInt(columnNames[112]), result.getInt(columnNames[113]), result.getInt(columnNames[114]), result.getInt(columnNames[115]), result.getInt(columnNames[116]), result.getInt(columnNames[117]),result.getInt(columnNames[118]), result.getInt(columnNames[119])
                ));
            }
            cn.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //
    //      Planned / Distributed
    //

    static public int[] selectPlannedData_or_distributedSum(boolean needPlannedData) {   // without distributed data
        Connection cn;
        Statement stat;
        ResultSet result;
        int[] data = new int[1];

        StringBuilder q = new StringBuilder("SELECT ");
        String query;

        if (needPlannedData) {     // get ЗАПЛАНОВАНО row (store in db)
            for (String column : columnNames) {
                q.append("`");
                q.append(column);
                q.append("`, ");
            }
            q.replace(q.length() - 2,q.length(), " FROM `DiplomaStudentsData` WHERE `name` = 'planned';");
        } else {                 // get РОЗПОДІЛЕНО row  (doesn't store in db - calculated in runtime)
            for (String column : columnNames) {
                q.append("SUM(`");
                q.append(column);
                q.append("`), ");
            }
            q.replace(q.length() - 2,q.length(), " FROM `DiplomaStudentsData` WHERE `name` != 'planned'");
        }
        query = q.toString();

        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            result = stat.executeQuery(query);
            while (result.next()) {
                data = new int[] {result.getInt(1),result.getInt(2),result.getInt(3), result.getInt(4), result.getInt(5), result.getInt(6),
                        result.getInt(7),result.getInt(8),result.getInt(9), result.getInt(10), result.getInt(11), result.getInt(12),
                        result.getInt(13),result.getInt(14),result.getInt(15), result.getInt(16), result.getInt(17), result.getInt(18),
                        result.getInt(19),result.getInt(20), result.getInt(21), result.getInt(22), result.getInt(23),
                        result.getInt(24), result.getInt(25), result.getInt(26), result.getInt(27), result.getInt(28), result.getInt(29),
                        result.getInt(30), result.getInt(31), result.getInt(32), result.getInt(33), result.getInt(34), result.getInt(35),
                        result.getInt(36), result.getInt(37), result.getInt(38), result.getInt(39), result.getInt(40), result.getInt(41),
                        result.getInt(42), result.getInt(43), result.getInt(44), result.getInt(45), result.getInt(46), result.getInt(47),
                        result.getInt(48), result.getInt(49), result.getInt(50), result.getInt(51), result.getInt(52), result.getInt(53),
                        result.getInt(54), result.getInt(55), result.getInt(56), result.getInt(57), result.getInt(58), result.getInt(59),
                        result.getInt(60),result.getInt(61),result.getInt(62), result.getInt(63), result.getInt(64), result.getInt(65), result.getInt(66),result.getInt(67), result.getInt(68), result.getInt(69),
                        result.getInt(70),result.getInt(71),result.getInt(72), result.getInt(73), result.getInt(74), result.getInt(75), result.getInt(76),result.getInt(77), result.getInt(78), result.getInt(79),
                        result.getInt(80),result.getInt(81),result.getInt(82), result.getInt(83), result.getInt(84), result.getInt(85), result.getInt(86),result.getInt(87), result.getInt(88), result.getInt(89),
                        result.getInt(90),result.getInt(91),result.getInt(92), result.getInt(93), result.getInt(94), result.getInt(95), result.getInt(96),result.getInt(97), result.getInt(98), result.getInt(99),
                        result.getInt(100),result.getInt(101),result.getInt(102), result.getInt(103), result.getInt(104), result.getInt(105), result.getInt(106),result.getInt(107), result.getInt(108), result.getInt(109),
                        result.getInt(110),result.getInt(111),result.getInt(112), result.getInt(113), result.getInt(114), result.getInt(115), result.getInt(116), result.getInt(117),result.getInt(118), result.getInt(119),
                        result.getInt(120)};
            }
            cn.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    static public String setUpDB() {
        Connection cn;
        Statement stat;
        ResultSet result;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "SELECT MAX(`id`) FROM `DiplomaStudentsData`;";
            System.out.println(query);
            result = stat.executeQuery(query);
            result.next();
            query = "INSERT INTO `DiplomaStudentsData` VALUES ("+ (result.getInt(1) + 1) +", 'planned', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);";
            System.out.println(query);
            stat.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    static public String updatePlanned_oneField(int column, int value) {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "UPDATE `DiplomaStudentsData` SET " + "`" + columnNames[column] + "` = " + value + " WHERE `name` = 'planned';";
            System.out.println(query);
            stat.executeUpdate(query);
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }
}