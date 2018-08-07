/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import com.vollyball.panels.PanRallyReport;
import com.vollyball.panels.PanRallyReportExt;

/**
 *
 * @author Supriya
 */
public class TotalRallies {

    String type;
    PanRallyReport pnPanRallyReport;
    PanRallyReportExt pnPanRallyReportExt;

    public PanRallyReport getPnPanRallyReport() {
        return pnPanRallyReport;
    }

    public void setPnPanRallyReport(PanRallyReport pnPanRallyReport) {
        this.pnPanRallyReport = pnPanRallyReport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PanRallyReportExt getPnPanRallyReportExt() {
        return pnPanRallyReportExt;
    }

    public void setPnPanRallyReportExt(PanRallyReportExt pnPanRallyReportExt) {
        this.pnPanRallyReportExt = pnPanRallyReportExt;
    }

}
