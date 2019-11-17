package com.sample.loadgitrepos;

import com.sample.api.RepositoryApiService;
import com.sample.model.RepositoryResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class GithubApiServiceTest extends ApiAbstract<RepositoryApiService> {

    private RepositoryApiService service;

    @Before
    public void initService() {
        this.service = createService(RepositoryApiService.class);
    }

    @Test
    public void fetchPostsTest() throws IOException {
       // enqueueResponse("test_repositories.json");

            HashMap data = new HashMap <String, String>();
            data.put("q", "created:>2019-10-16");
            data.put("sort", "stars");
            data.put("order", "desc");
            //val apiService = RepositoryApiBuilder().service
     //   Call response = service.getRepositoryList(data);
        Assert.assertEquals(true, data);


    }
}
