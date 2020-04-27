package dtu.bachelor.framework.service;

import dtu.bachelor.framework.model.Measurement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Interpolation {

    public void Interpolate(List<Measurement> locationList, String interpolationType){
        switch (interpolationType.toLowerCase()){
            case "quadratic":
                QuadraticInterpolation(locationList);
            case "cubic":
                CubicInterpolation(locationList);
            default: // The default interpolation type is linear
                LinearInterpolation(locationList);
        }
    }

    private void LinearInterpolation(List<Measurement> locationList) {
        List<Measurement> noInterpolate_list = new ArrayList<>();
        List<Measurement> interpolate_list = new ArrayList<>();
        for (Measurement measurement : locationList){
            if (measurement.getLatitude() == 0.0 & measurement.getLongitude() == 0.0){
                interpolate_list.add(measurement);
            } else {
                noInterpolate_list.add(measurement);
            }
        }

        for (int i = 0 ; i < noInterpolate_list.size() - 1 ; i++){
            for (Measurement point : interpolate_list){
                if (point.getTime().isAfter(noInterpolate_list.get(i).getTime()) & point.getTime().isBefore(noInterpolate_list.get(i + 1).getTime())){
                    Timestamp pointtimestamp = Timestamp.valueOf(point.getTime());
                    Timestamp noInttimestamp = Timestamp.valueOf(noInterpolate_list.get(i).getTime());
                    Timestamp nexttimestamp = Timestamp.valueOf(noInterpolate_list.get(i + 1).getTime());

                    point.setLatitude(noInterpolate_list.get(i).getLatitude() + (noInterpolate_list.get(i + 1).getLatitude() - noInterpolate_list.get(i).getLatitude()) * ((pointtimestamp.getTime() - noInttimestamp.getTime()) / (nexttimestamp.getTime() - noInttimestamp.getTime())));
                    point.setLongitude(noInterpolate_list.get(i).getLongitude() + (noInterpolate_list.get(i + 1).getLongitude() - noInterpolate_list.get(i).getLongitude()) * ((pointtimestamp.getTime() - noInttimestamp.getTime()) / (nexttimestamp.getTime() - noInttimestamp.getTime())));
                }
            }
        }
    }

   private void QuadraticInterpolation(List<Measurement> locationList) {
       List<Measurement> noInterpolate_list = new ArrayList<>();
       List<Measurement> interpolate_list = new ArrayList<>();
       for (Measurement measurement : locationList){
           if (measurement.getLatitude() == 0.0 & measurement.getLongitude() == 0.0){
               interpolate_list.add(measurement);
           } else {
               noInterpolate_list.add(measurement);
           }
       }

       int k;

       for (int i = 0 ; i < noInterpolate_list.size() ; i++) {
           for (Measurement point : interpolate_list) {
               if (point.getTime().isAfter(noInterpolate_list.get(i).getTime()) & point.getTime().isBefore(noInterpolate_list.get(i + 1).getTime())){

                   if (i == 0) {
                       k = i;
                   } else {
                       if (i == noInterpolate_list.size() - 1){
                           k = i - 2;
                       } else {
                           k = i - 1;
                       }
                   }
                   Timestamp pointtimestamp = Timestamp.valueOf(point.getTime());
                   Timestamp noInttimestamp = Timestamp.valueOf(noInterpolate_list.get(i).getTime());
                   Timestamp nexttimestamp = Timestamp.valueOf(noInterpolate_list.get(i + 1).getTime());
                   Timestamp nextnexttimestamp = Timestamp.valueOf(noInterpolate_list.get(i + 2).getTime());


                   //P2(x) = y0L0(x) + y1L1(x) + y2L2(x) = y0(x−x1)(x−x2)(x0−x1)(x0−x2) + y1(x−x0)(x−x2)(x1−x0)(x1−x2) + y2(x−x0)(x−x1)(x2−x0)(x2−x1)

                   long X0_X1 = (noInttimestamp.getTime() - nexttimestamp.getTime());
                   long X0_X2 = (noInttimestamp.getTime() - nextnexttimestamp.getTime());

                   long X1_X0 = (nexttimestamp.getTime() - noInttimestamp.getTime());
                   long X1_X2 = (nexttimestamp.getTime() - nextnexttimestamp.getTime());

                   long X2_X0 = (nextnexttimestamp.getTime() - noInttimestamp.getTime());
                   long X2_X1 = (nextnexttimestamp.getTime() - nexttimestamp.getTime());

                   long L0 = ((pointtimestamp.getTime() - nexttimestamp.getTime()) * (pointtimestamp.getTime() - nextnexttimestamp.getTime())) / ((X0_X1) * (X0_X2));
                   long L1 = ((pointtimestamp.getTime() - noInttimestamp.getTime()) * (pointtimestamp.getTime() - nextnexttimestamp.getTime())) / ((X1_X0) * (X1_X2));
                   long L2 = ((pointtimestamp.getTime() - noInttimestamp.getTime()) * (pointtimestamp.getTime() - nexttimestamp.getTime())) / ((X2_X0) * (X2_X1));

                   point.setLatitude(noInterpolate_list.get(k).getLatitude() * (L0) + noInterpolate_list.get(k + 1).getLatitude() * (L1) + noInterpolate_list.get(k + 2).getLatitude() * (L2));
                   point.setLongitude(noInterpolate_list.get(k).getLongitude() * (L0) + noInterpolate_list.get(k + 1).getLongitude() * (L1) + noInterpolate_list.get(k + 2).getLongitude() * (L2));
               }
           }
       }
   }

    private void CubicInterpolation(List<Measurement> locationList) {
    }
}