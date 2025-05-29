package phuongKhueTrung;

import phuongHoaAn.FPTCorporation;

public class VinGroup {

    //Ngoài class khác package
    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "Vin LCD";
        fpt.setTvName();

       // fpt.tvColor= "";
        //fpt.setTvColor();
    }
}
