package com.aditya.battleship;

import com.aditya.ResponseEnum.ResponseEnum;
import com.aditya.model.Location;

public interface IBattleship {
	ResponseEnum isShipTargeted(Location position);
}
