package dtu.bachelor.framework.service;

import java.util.List;

public class Interpolation {

    void Interpolate(List<Double> list, String interpolatetype) {
        switch (interpolatetype.toLowerCase()){
            case "linear":
                break;
            case "cubic":
                break;
            default:
                // This will interpolate the geolocations as quadratic
                break;
        }
    }
}
