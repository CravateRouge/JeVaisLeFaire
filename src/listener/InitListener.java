package listener;

import java.util.EventListener;
import java.util.List;

import enumeration.TypeBateau;
import enumeration.TypeBattle;

public interface InitListener extends EventListener {

	public void initGame(String j1Nom, String j2Nom);

}
