package com.example.electriccircuit.Components;

public class Budget {

    final double WIREBASE = 1.00;
    final double CAPACITORBASE = 5.00;
    final double POWERSUPPLYBASE = 10.00;
    final double RESISTORBASE = 2.00;
    double budget;

    public void setBudget(double budget){
        this.budget = budget;
    }
    public double getBudget(){
        return budget;
    }
    public double createWirePrice(Wire wire) {
        return (wire.getLength() * WIREBASE);
    }
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