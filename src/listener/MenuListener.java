package listener;

import java.util.EventListener;

import enumeration.TypeBateau;
import enumeration.TypeMode;

public interface MenuListener extends EventListener {

	public void j1NameChoisi();

	public void battleChoisie();

	public void modeChoisi(TypeMode mode);

	public void initGame(String j1Nom, String j2Nom, TypeBateau boat);

}
