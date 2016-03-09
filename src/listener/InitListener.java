package listener;

import java.util.EventListener;
import java.util.List;

import enumeration.TypeBateau;
import enumeration.TypeBattle;

public interface InitListener extends EventListener {

	public void initGame(TypeBattle battle, String j1Name, String j2Name, List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte);

}
