    import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.List;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.Timer;

	public class BlackJackListener implements ActionListener {
		private JButton jbPlusBet;
		private JButton jbHitBut;
		private JButton jbStand;
		private JLabel jlbBetText;
		private int BET=0;
		private boolean wantsHit;

		public BlackJackListener(JButton jbPlusBet, JButton jbHitBut, JButton jbStand, JLabel jlbBetText,Boolean wantsHit) {

			this.jbPlusBet= jbPlusBet;
			this.jbHitBut=jbHitBut;
			this.jbStand = jbStand;
			this.wantsHit=wantsHit;
			this.jlbBetText=jlbBetText;
			
			
		}

		@Override
		public void actionPerformed(ActionEvent eevee) {
			if(eevee.getSource() instanceof JButton){
				if(eevee.getSource() ==jbPlusBet){
					System.out.println("hit plus");
					//Increase Bet and display on JLabel
					BET=BET+10;
					jlbBetText.setText(BET+"");
				}
				if(eevee.getSource()==jbStand){
					
					wantsHit=false;
					
				}
				if(eevee.getSource()==jbHitBut){
					
					//Hit when asked
					wantsHit=true;
				}
				
			}
			
			
		}
}
