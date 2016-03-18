package listener;

import java.util.EventListener;

import enumeration.TypeBateau;

public interface PoseBateauListener extends EventListener {

	public void poseBateau(TypeBateau currentBoat);

}
