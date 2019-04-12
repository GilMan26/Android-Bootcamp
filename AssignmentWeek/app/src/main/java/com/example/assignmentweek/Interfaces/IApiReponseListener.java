package com.example.assignmentweek.Interfaces;

import com.example.assignmentweek.Response.CreateResponse;
import com.example.assignmentweek.Response.Datum;

import java.util.List;

public interface IApiReponseListener {

    void dataListResponse(List<Datum> list);

    void addUsserResponse(CreateResponse createResponse);

}
