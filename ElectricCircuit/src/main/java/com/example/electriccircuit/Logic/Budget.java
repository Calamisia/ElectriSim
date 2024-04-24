package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.Capacitors;
import com.example.electriccircuit.Components.PowerSupply;
import com.example.electriccircuit.Components.Resistors;
import com.example.electriccircuit.Components.Wire;

public class Budget {

    // Price scalars for each component
    final double WIREBASE = 1.00;
    final double CAPACITORBASE = 5.00;
    final double POWERSUPPLYBASE = 10.00;
    final double RESISTORBASE = 2.00;
    double budget; // the budget will be assigned for each level

    public void setBudget(double budget){
        this.budget = budget;
    } // set budget
    public double getBudget(){
        return budget;
    } // get budget

    // determine the price of components
    //public double createWirePrice(Wire wire) {
      //  return (wire.getLength() * WIREBASE);
    //}

    public double createCapacitorPrice(Capacitors capacitor) {
        return (capacitor.getCapacitance() * CAPACITORBASE);
    }

    public double createPowerSupplyPrice(PowerSupply powerSupply) {
        return (powerSupply.getVoltage() * POWERSUPPLYBASE);
    }

    public double createResistorPrice(Resistors resistor) {
        return (resistor.getResistance() * RESISTORBASE);
    }

}
