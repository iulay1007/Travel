package com.example.travel.view;

import com.example.travel.model.domain.SecondListResponse;
import com.example.travel.model.domain.TagList;
import com.example.travel.model.domain.Tagl;

import java.util.List;

public interface IDialogListCallback {
    void onTagListLoad (List<Tagl> listResponses);

}
