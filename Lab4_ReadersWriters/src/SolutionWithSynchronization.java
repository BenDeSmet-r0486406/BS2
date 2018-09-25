
public class SolutionWithSynchronization implements MemoryWrapper {
	private MemorySegment _memory = null;
	private synchronized ArrayList()

    public SolutionWithSynchronization(){
	_memory = new MemorySegment();
    }

    public void read(Process p){
    	p.setState("wantread");
    	while(p.getState()=="writing"){
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	p.setState("reading");
    	_memory.read();	
    	p.setState("idle");
    	notifyAll();
    }

    public void write(Process p){
    	p.setState("wantwrite");
    	while(p.getState()!="idle" || p.getState()!="wantwrite" ){
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	p.setState("writing");
    	_memory.write();
    	p.setState("idle");
    	notify();
    }
}
