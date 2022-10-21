package Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);             //начальное смещение X
        tt.setByX(20f);              //конечное смещение X
        tt.setFromY(0f);             //начальное смещение Y
        tt.setByY(20f);              //конечное смещение Y
        tt.setCycleCount(4);         //число повторений
        tt.setAutoReverse(true);     //возврат node-а на место
    }

    //метод включения анимации
    public void playAnimation() {
        tt.playFromStart();
    }
}
