package listener;

import java.util.EventListener;

import enumeration.TypeMode;

public interface ModeListener extends EventListener {

	public void modeChoisi(TypeMode mode);

}