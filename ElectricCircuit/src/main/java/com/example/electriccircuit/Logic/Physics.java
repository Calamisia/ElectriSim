package com.example.electriccircuit.Logic;

import com.example.electriccircuit.DataTypes.*;

public class Physics  {

    // ohmsLaw for resistance
    public double ohmsLaw(Volt potential, Amp current){
        return (potential.getVolt() / current.getAmp());
    }
    // ohmsLaw for potential
    public double ohmsLaw(Amp current, Ohm resistance){
        return (current.getAmp()*resistance.getOhm());
    }
    // ohmsLaw for current
    public double ohmsLaw(Volt potential, Ohm resistance){
        return (potential.getVolt() / resistance.getOhm());
    }



    // powerLaw for watts
    public double powerLaw(Volt potential, Amp current) {
        return (potential.getVolt() * current.getAmp());
    }
    // powerLaw for voltage
    public double powerLaw(Watt power, Amp current) {
        return (power.getWatt() / current.getAmp());
    }
    // powerLaw for current
    public double powerLaw(Volt potential, Watt power) {
        return (power.getWatt() / potential.getVolt());
    }

    // capacitanceLaw for couloumbs
    public double capacitanceLaw(Capacitance farad, Volt potential) {
        return (farad.getCapacitance() * potential.getVolt());
    }
    // capacitanceLaw for farads
    public double capacitanceLaw(Charge coulomb, Volt potential) {
        return (coulomb.getCharge() / potential.getVolt());
    }
    // capacitanceLaw for volts
    public double capacitanceLaw(Charge coulomb, Capacitance farad) {
        return (coulomb.getCharge() / farad.getCapacitance());
    }
}
