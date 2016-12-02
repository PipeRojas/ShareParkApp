package eci.cosw.edu.sharepark.network;

import java.io.IOException;
import java.util.List;

import eci.cosw.edu.sharepark.entities.Parking;
import eci.cosw.edu.sharepark.network.service.ParkingService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 2098099 on 11/16/16.
 */

public class Network{
    private final ParkingService service;
    public Network() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sharepark.herokuapp.com/parkings/").addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(ParkingService.class);
    }

    public List<Parking> getParkings() throws IOException {
        Call<List<Parking>> call=service.listParkings();
        Response<List<Parking>> response = call.execute();
        return response.body();
    }


}
