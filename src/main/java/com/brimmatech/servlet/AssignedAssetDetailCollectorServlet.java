package com.brimmatech.servlet;

import com.brimmatech.dao.AssetDetailsClass;
import com.brimmatech.dao.AssignedAssetDetailCollector;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/assigned")
public class AssignedAssetDetailCollectorServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            AssignedAssetDetailCollector assignedAssetDetailCollector = new AssignedAssetDetailCollector();
            List<AssetDetailsClass> list = assignedAssetDetailCollector.assignedAssetDetail();
            request.setAttribute("list", list);
            String userJsonString = this.gson.toJson(list);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
