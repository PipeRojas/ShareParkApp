package eci.cosw.edu.sharepark.network.service;

import java.util.List;

import eci.cosw.edu.sharepark.entities.Parking;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 2098099 on 11/16/16.
 */

public interface ParkingService {
    @GET("teams.json")
    Call<List<Parking>> listParkings();
}
