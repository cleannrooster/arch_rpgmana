package com.cleannrooster.rpgmana.api;

import net.spell_engine.api.spell.Spell;

import java.util.List;

public interface ManaInterface {
    double getMana();
    double getMaxMana();
    float getManaRegen();
    void addManaRPG(float mana);
    double spendMana(double toadd);
    int getTimeFull();
    void setMana(float mana);
    List<ManaInstance> getManaInstances();
    Spell getLastSpell();
    void setLastSpell(Spell spell);
}
