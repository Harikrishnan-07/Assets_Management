package com.brimmatech.servlet;

import com.brimmatech.dao.AssetUpdatorDetail;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updator")
public class AssetUpdatorDetailServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            JSONObject jsonObject = new JSONObject(builder.toString());

            String serialnumber = jsonObject.getString("serialnumber");
            String modelnumber = jsonObject.getString("modelnumber");
            String assettype = jsonObject.getString("assettype");
            String assetname = jsonObject.getString("assetname");
            String status = jsonObject.getString("status");
            String reason = jsonObject.getString("reason");

            AssetUpdatorDetail assetUpdatorDetail = new AssetUpdatorDetail();
            assetUpdatorDetail.assetUpdator(serialnumber, modelnumber, assettype, assetname, status, reason);

            String userJsonString = this.gson.toJson("Successfully entered");
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();

        } catch (JSONException | IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
