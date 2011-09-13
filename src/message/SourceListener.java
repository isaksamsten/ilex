package message;
import java.util.EventListener;

public interface SourceListener extends EventListener {
	void line(int num, String line);
}
