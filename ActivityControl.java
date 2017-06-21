import java.rmi.RemoteException;
public class ActivityControl {        //Invoker of Activity
	  private Activity activity;
	  public void setActivity(Activity activity){
	    this.activity = activity;
	  }
	  public void choose() throws RemoteException{
	    activity.execute();    //selected activity is executed
	  }
}
