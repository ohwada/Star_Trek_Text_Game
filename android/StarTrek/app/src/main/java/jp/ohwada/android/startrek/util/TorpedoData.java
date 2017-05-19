/**
  * STAR TREK
 * 2017-05-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.util; 

	import  java.lang.Math;
    import java.util.List;

/*
 * TorpedoData
 */ 
public class TorpedoData {
    public List<TorpedoTarget> list = null;
    public String[][] map = null;
    public String report = "";

    
    /**
     * constructor
     */
    public TorpedoData( List<TorpedoTarget> _list, String[][] _map, String _report ) {
       this.list = _list;
       this.map = _map;
              this.report = _report;
    } // public

} //  end of  class

