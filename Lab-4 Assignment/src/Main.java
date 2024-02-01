//Roll:2007111 - Plaban Das

/*
Here, I am using two design patterns.
A bridge design pattern & A proxy design pattern
*/
interface TV{
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int vol);

    int getChannel();

    void setChannel(int channel);

}

class SmartTV implements TV{
    boolean powerOn=false;
    int volume=50;
    int channel=2;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int vol) {
        volume=vol;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel=channel;
    }
    public void Youtube(YoutubeClass yt){
        System.out.println("Youtube for SmartTV -> Is Called");
        yt.runYoutube();
    }
}

class GeneralTV implements TV{
    boolean powerOn=false;
    int volume=30;
    int channel=2;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int vol) {
        volume=vol;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel=channel;
    }
}


class Remote{
    protected TV tv;

    Remote(){tv=null;}
    Remote(TV tv){
        this.tv=tv;
    }
    public void togglePower() {
        if(tv.isEnabled()){
            tv.disable();
            System.out.println("Power turned off -> Is Called");
        }
        else {
            tv.enable();
            System.out.println("Power turned on -> Is Called");
        }
    }

    public void volumeUp() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()+5);
            System.out.println("Increasing volume +5 -> Is Called");
        }else{
            System.out.println("Please Turn on tv first -> Is Called");
        }
    }

    public void volumeDown() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()-5);
            System.out.println("Decreasing volume -5 -> Is Called");
        }else{
            System.out.println("Please Turn on tv first -> Is Called");
        }

    }

    public void channelUp() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()+1);
            System.out.println("Channel +1 -> Is Called");
        }else{
            System.out.println("Please Turn on tv first -> Is Called");
        }


    }

    public void channelDown() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()-1);
            System.out.println("Channel -1 -> Is Called");
        }else{
            System.out.println("Please Turn on tv first -> Is Called");
        }

    }
}

class SmartRemote extends Remote{

    SmartRemote(){

    }
    SmartRemote(TV tv){
        super(tv);
    }
    void showYoutube(YoutubeClass yt){
        ((SmartTV)tv).Youtube(yt);
    }
}

//YoutubeCLass implements Proxy Design method
interface YoutubeClass{
    void runYoutube();
}
class FirstLoadYoutube implements YoutubeClass{

    @Override
    public void runYoutube() {
        System.out.println("Running Youtube -> Is Called");
    }
}

class proxyLoadYoutube implements YoutubeClass{
    private FirstLoadYoutube firstLoadYoutube;
    @Override
    public void runYoutube() {
        if(firstLoadYoutube==null){
            firstLoadYoutube=new FirstLoadYoutube();
            System.out.println("Starting Youtube -> Is Called");
        }
        firstLoadYoutube.runYoutube();
    }
}

public class Main {
    public static void main(String[] args) {


        //GeneralTV

        GeneralTV gtv=new GeneralTV();
        Remote remote= new Remote(gtv);
        System.out.println("General Tv -> Is Called");
        remote.togglePower();
        remote.volumeUp();
        remote.channelUp();
        remote.channelDown();
        remote.volumeDown();

        //SmartTV
        System.out.println();
        System.out.println("Smart Tv -> Is Called");
        SmartTV stv=new SmartTV();
        SmartRemote sremote= new SmartRemote(stv);
        sremote.togglePower();
        sremote.volumeUp();
        sremote.channelUp();
        sremote.channelDown();
        sremote.volumeDown();

        System.out.println();
        //Youtube Class
        YoutubeClass yt=new proxyLoadYoutube();

        //Accessing Youtube() using smart-remote.
        sremote.showYoutube(yt);
        sremote.showYoutube(yt);
    }

}