/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class Player {

	public enum PlayerType {Healer, Tank, Samurai, BlackMage, Phoenix, Cherry};
	
	private PlayerType type; 	//Type of this player. Can be one of either Healer, Tank, Samurai, BlackMage, or Phoenix
	
        private double maxHP;		//Max HP of this player
	private double currentHP;	//Current HP of this player 
	private double atk;			//Attack power of this player
        
	private int internalcount;
	private int numberOfSpecialTurns;
        private int SpecialCount;
        private int position;
	
        private Player player_cursed = null;
	private String teamName;
	private String Row;
	
	private boolean isTaunt;
	private boolean isCursed;
	private boolean isSleeeping;
        private boolean alive;
	/**
	 * Constructor of class Player, which initializes this player's type, maxHP, atk, numSpecialTurns, 
	 * as specified in the given table. It also reset the internal turn count of this player. 
	 * @param _type
	 */
	public Player(PlayerType _type)
	{
		type = _type;
          switch(type)
          {
              case Healer:
                  maxHP = 4790 ;
                  currentHP = maxHP;
                  atk = 238;
                  numberOfSpecialTurns = 4;
                  SpecialCount = numberOfSpecialTurns;
                  break;
              case Tank:
                  maxHP = 5340 ;
                  currentHP = maxHP;
                  atk = 255;
                  numberOfSpecialTurns = 4;
                  SpecialCount = numberOfSpecialTurns;
                  break;
              case Samurai: 
                  maxHP = 4005 ;
                  currentHP = maxHP;
                  atk = 368;
                  numberOfSpecialTurns = 3;
                  SpecialCount = numberOfSpecialTurns;
                  break;
              case BlackMage:
                  maxHP = 4175 ;
                  currentHP = maxHP;
                  atk = 303;
                  numberOfSpecialTurns = 4;
                  SpecialCount = numberOfSpecialTurns;
                  break;
              case Phoenix:
                  maxHP = 4175 ;
                  currentHP = maxHP;
                  atk = 209;
                  numberOfSpecialTurns = 8;
                  SpecialCount = numberOfSpecialTurns;
                  break;
              case Cherry:
                  maxHP = 3560 ;
                  currentHP = maxHP;
                  atk = 198;
                  numberOfSpecialTurns = 4;
                  SpecialCount = numberOfSpecialTurns;
                  break;
          }
	}

	public double getCurrentHP()
	{
		return this.currentHP; //Returns the current HP of this player
	}
	
	public Player.PlayerType getType()
	{
		return this.type; //Return Value Type of This Player
	}

	public double getMaxHP()
	{
		
		return this.maxHP; //Returns max HP of this player. 
	}
	
	public boolean isSleeping()
	{	
		return isSleeeping; //Return Who is sleeping;
	}

	public boolean isCursed()
	{
		return isCursed; //Return Who is cursed
	}
	
	public boolean isAlive()
	{
            return this.currentHP>0; //Return Who Never Die
	}
	
	public boolean isTaunting()
	{
		return isTaunt; //Return To want to taunt
	}
	
	public void setTeam(String team) {
		this.teamName = team; //Use this to call from Arena
	}
	public String getTeam() {
		return this.teamName; //Return Team Hero from each Row
	}
	public void setRow(String row) {
		this.Row = row; //Set Hero from each Row
	}
	public String getRow() {
		return Row; //Return Hero from each Row
	}
	public void setPosition(int position) {
		this.position = position; //Use this to call from Arena
	}
	public int getPosition() {
		return this.position; //Return Position of Hero
	}
	public void addInternalcount() {
		this.internalcount++;
	}
	public void setTaunt(boolean isTaunt) {
		this.isTaunt = isTaunt;
	}
	public void setCursed(boolean isCursed) {
		this.isCursed = isCursed;
	}
	public void setSleeping(boolean isSleeping) {
		this.isSleeeping = isSleeping;
	}
     
	public void attack(Player target)
	{	
			target.currentHP -= this.atk;
		SpecialCount--;
		if (target.currentHP <= 0) {
			target.currentHP = 0;
			target.alive = false;
		}
	}
	public Player IsTaunting(Player[][] team) {
		for (int i = 0; i < 2; i++) {
                    for (Player item : team[i]) {
                        if (item.isTaunt) {
                            return item;
                        }
                    }
		}
		return null;
	}
	
    /**
     *
     * @param myTeam
     * @param theirTeam
     */
    public void useSpecialAbility(Player[][] myTeam, Player[][] theirTeam) // Instead by Switch-Case
	{
		internalcount = 0;
		if(null != this.type) switch (this.type) {
                case Healer:
                    this.Healing(myTeam);
                    break;
                case Tank:
                    Player[][] Playerwant2Tank = this.Playerwant2Tank(theirTeam);
                    break;
                case BlackMage:
                    Player[][] CursedXhim = this.CursedXhim(theirTeam);
                    break;
                case Phoenix:
                    Player[][] Revive = this.Revive(myTeam);
                    break;
                case Cherry:
                    Player[][] FortuneCookie = this.FortuneCookie(theirTeam);
                    break;
                default: System.out.println("Error");
                    break;
            }
	}
        
        public void Healing(Player[][] myTeam) // Function to use by Healer
        {
            Player lowest = findWhoNeed2Heal(myTeam);
			if(lowest.isCursed) {
				return ;
			}
			if(lowest.currentHP > 0 && lowest.currentHP<lowest.maxHP && lowest.isCursed == false) {
				System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Heals "+lowest.getTeam()+"["+lowest.getRow()+"]"+"["+lowest.getPosition()+"]"+" {"+lowest.type.toString()+"}");
				lowest.currentHP += (0.25*lowest.maxHP);
				if(lowest.currentHP>lowest.maxHP) {
					lowest.currentHP = lowest.maxHP;
				}
			}
        }
        
        public Player[][] Playerwant2Tank(Player[][] theirTeam) // Tank need this Function
        {
            System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" is Taunting ");
			setTaunt(true);
            return theirTeam;
        }
        
        public Player[][] CursedXhim(Player[][] theirTeam) // Blackmage need this Function
        {
            Player curse = WhoisCursed(theirTeam);
			if(IsTaunting(theirTeam) != null) {
				curse = IsTaunting(theirTeam);
			}
			if (curse != null) {
				System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Curses "+curse.getTeam()+"["+curse.getRow()+"]"+"["+curse.getPosition()+"]"+" {"+curse.type.toString()+"}");
				curse.setCursed(true);
				player_cursed = curse;
			}
            return theirTeam;
        }
         
        public Player[][] Revive(Player[][] myTeam) //Pheonix Need this to revive
        {
            Player dead = myTeam[0][0];
			boolean find_dead = false;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < myTeam[0].length; j++) {
					if(!myTeam[i][j].isAlive() && find_dead == false) {
						dead = myTeam[i][j];
						find_dead = true;
					}
				}
			}
			System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Revives "+dead.getTeam()+"["+dead.getRow()+"]"+"["+dead.getPosition()+"]"+" {"+dead.type.toString()+"}");
			dead.currentHP = dead.currentHP + (dead.maxHP*0.3);
			dead.setTaunt(false);
			dead.setCursed(false);
			dead.internalcount = 0;
            return myTeam;
            
        }
        
	public Player[][] FortuneCookie(Player[][] theirTeam) //Cherry(Cherprang) use this to sing to opsTeam
        {
            for (int i = 0; i < 2; i++) {
				for (int j = 0; j < theirTeam[0].length; j++) {
					if(theirTeam[i][j].isAlive()) {
						System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Feeds a Fortune Cookies to "+theirTeam[i][j].getTeam()+"["+theirTeam[i][j].getRow()+"]"+"["+theirTeam[i][j].getPosition()+"]"+" {"+theirTeam[i][j].type.toString()+"}");
						theirTeam[i][j].setSleeping(true);
                                                System.out.println("# " + theirTeam[i][j].getTeam() + "[" + theirTeam[i][j].getRow() + "]" + "[" + theirTeam[i][j].getPosition() + "]" + " {"
						+ theirTeam[i][j].getType() + "}" + " Sleep");
					}
				}
			}
            return theirTeam;
        }
	
	/**
	 * This method is called by Arena when it is this player's turn to take an action. 
	 * By default, the player simply just "attack(target)". However, once this player has 
	 * fought for "numSpecialTurns" rounds, this player must perform "useSpecialAbility(myTeam, theirTeam)"
	 * where each player type performs his own special move. 
	 * @param arena
	 */
	public void takeAction(Arena arena)
	{	
		if(this.type == PlayerType.BlackMage && player_cursed != null) {
			player_cursed.setCursed(false);
			player_cursed = null;
		}
		if(this.isTaunt) {
			this.setTaunt(false);
		}
		if(this.isSleeeping) {
			this.setSleeping(false);
			return;
		}
		addInternalcount();
		
		Player[][] myteam = null;
		Player[][] opp = null;
		if(arena.getMyTeam(this) == arena.getTeamA()) {
			 myteam = arena.getTeamA();
			 opp = arena.getTeamB();	
		}else if(arena.getMyTeam(this) == arena.getTeamB()) {
			 myteam = arena.getTeamB();
			 opp = arena.getTeamA();
		}
		
		if(this.type == PlayerType.Healer && internalcount == this.numberOfSpecialTurns) {
			useSpecialAbility(myteam, opp);
		}else if(this.type == PlayerType.Tank && internalcount == this.numberOfSpecialTurns) {
			useSpecialAbility(myteam, opp);
		}else if(this.type == PlayerType.Samurai && internalcount == this.numberOfSpecialTurns) {
			internalcount = 0;
			Player target = arena.findTarget(this);
			if(target == null) {
				return;
			}
				System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Double-Slash "+target.getTeam()+"["+target.getRow()+"]"+"["+target.getPosition()+"]"+" {"+target.type.toString()+"}");
				attack(target);
				attack(target);
		}else if (this.type == PlayerType.BlackMage && internalcount == this.numberOfSpecialTurns) {
			useSpecialAbility(myteam, opp);
		}else if (this.type == PlayerType.Phoenix && internalcount == this.numberOfSpecialTurns) {
			useSpecialAbility(myteam, opp);
		}else if (this.type == PlayerType.Cherry && internalcount == this.numberOfSpecialTurns) {
			useSpecialAbility(myteam, opp);
		}else {
			Player target = arena.findTarget(this);
			if(target != null) {
				attack(target);
				
				System.out.println("# "+this.getTeam()+"["+this.getRow()+"]"+"["+this.getPosition()+"]"+" {"+this.type.toString()+"}"+" Attacks "+target.getTeam()+"["+target.getRow()+"]"+"["+target.getPosition()+"]"+" {"+target.type.toString()+"}");
			}
		}
		
		for (int i = 0; i < 2; i++) {
                    for (Player item : opp[i]) {
                        if (!item.isAlive()) {
                            item.isCursed = false;
                            item.isTaunt = false;
                            item.isSleeeping = false;
                        }
                    }
		}
	}
	
	/**
	 * This method overrides the default Object's toString() and is already implemented
     * @return for you. 
	 */
	@Override
	public String toString()
	{
		return "["+this.type.toString()+" HP:"+this.currentHP+"/"+this.maxHP+" ATK:"+this.atk+"]["
				+((this.isCursed())?"C":"")
				+((this.isTaunting())?"T":"")
				+((this.isSleeping())?"S":"")
				+"]";
	}
	

	
	public Player findWhoNeed2Heal(Player[][] myteam) {
		double mininum = 9999;
		Player target = null;
		
		for (int i = 0; i < 2; i++) {
			if(i==0) {
                            for (Player item : myteam[i]) {
                                if (item.getCurrentHP() > 0 && item.getCurrentHP() / (item.getMaxHP()) * 100 < mininum) {
                                    mininum = item.getCurrentHP() / (item.getMaxHP()) * 100;
                                    target = item;
                                }
                            }
			}else {
                            for (Player item : myteam[i]) {
                                if (item.getCurrentHP() > 0 && item.getCurrentHP() / (item.getMaxHP()) * 100 < mininum) {
                                    mininum = item.getCurrentHP() / (item.getMaxHP()) * 100;
                                    target = item;
                                }
                            }
			}
		}
		return target;
	}
	public Player WhoisCursed(Player[][] theirTeam) {
		double mininum = 9999;
		Player target = null;
		boolean findcurse = false;
		for (int i = 0; i < 2; i++) {
			if(i!=0) {
                            for (Player item : theirTeam[i]) {
                                if (item.isAlive() && item.currentHP < mininum && findcurse == false) {
                                    mininum = item.currentHP;
                                    target = item;
                                    findcurse = true;
                                }
                            }
                        }else {
                            for (Player item : theirTeam[i]) {
                                if (item.isAlive() && item.currentHP < mininum) {
                                    mininum = item.currentHP;
                                    target = item;
                                    findcurse = true;
                                }
                            }
                        }
		}
		return target;
	}
}