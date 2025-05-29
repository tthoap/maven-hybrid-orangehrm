package phuongHoaAn;

public class TPBank {

    //Phạm vi ngoài class nhưng trong cùng package
    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "TPBank LCD";
        fpt.setTvName();

        fpt.setTvColor();

        fpt.tvChannel = "";
        fpt.setTvChannel();

        //fpt.tvVolumn = "";
    }
}
