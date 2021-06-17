package com.example.travel.view;

import com.example.travel.base.IBaseCallback;
import com.example.travel.model.domain.FirstListResponse;
import com.example.travel.model.domain.SecondListCategoryResponse;

import java.util.List;

public interface IFirstListCallback extends IBaseCallback {


    void onFirstListLoad (List<FirstListResponse> listResponses);
}
