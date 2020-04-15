package dtu.bachelor.framework.service;

import java.util.List;

public class Interpolation {

  /*  List<DoubleList> Interpolatetest(List<DoubleList> locationlist,String interpolationtype){
        switch (interpolationtype.toLowerCase()){
            case "quadratic":
                return QuadraticInterpolation(locationlist);
            case "cubic":
                return CubicInterpolation(locationlist);
            default: // The default interpolation type is linear
                return LinearInterpolation(locationlist);
        }
    }*/

    void Interpolatetest(List<DoubleList> locationlist,String interpolationtype){
        switch (interpolationtype.toLowerCase()){
            case "quadratic":
                QuadraticInterpolation(locationlist);
            case "cubic":
                CubicInterpolation(locationlist);
            default: // The default interpolation type is linear
                LinearInterpolation(locationlist);
        }
    }

   /* private List<DoubleList> QuadraticInterpolation(List<DoubleList> locationlist) {
    }

    private List<DoubleList> CubicInterpolation(List<DoubleList> locationlist) {
    }

    private List<DoubleList> LinearInterpolation(List<DoubleList> locationlist) {
    }*/
   private void QuadraticInterpolation(List<DoubleList> locationlist) {
   }

    private void CubicInterpolation(List<DoubleList> locationlist) {
    }

    private void LinearInterpolation(List<DoubleList> locationlist) {
       int c = -1;
       for (int i = 0; i<locationlist.size()-1;i++){
           for (DoubleList point : locationlist){
           }
       }
    }
}

class DoubleList {
    public double latitude;
    public double longitude;

    public DoubleList(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
