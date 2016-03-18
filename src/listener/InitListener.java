package listener;

import java.util.EventListener;
import java.util.List;

import enumeration.TypeBateau;
import enumeration.TypeBattle;

public interface InitListener extends EventListener {

	public void initGame(TypeBattle battle, String j1Name, String j2Name, int taille, TypeBateau currentBoat);

}
