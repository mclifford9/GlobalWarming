package net.porillo.effect.neutral;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.porillo.GlobalWarming;
import net.porillo.effect.ClimateData;
import net.porillo.effect.api.ClimateEffectType;
import net.porillo.effect.api.ListenerClimateEffect;
import net.porillo.engine.ClimateEngine;
import net.porillo.engine.api.Distribution;
import net.porillo.engine.api.WorldClimateEngine;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.HashMap;
import java.util.Map;

@ClimateData(type = ClimateEffectType.FARM_YIELD)
public class FarmYield extends ListenerClimateEffect {

    private HashMap<Material, Distribution> cropDistribution;

    @EventHandler
    public void onCropGrow(BlockGrowEvent event) {
        if (!ClimateEngine.getInstance().isEffectEnabled(event.getBlock().getWorld().getName(), ClimateEffectType.FARM_YIELD)) {
            return;
        }

        Distribution distribution = cropDistribution.get(event.getBlock().getType());
        if (distribution != null) {
            WorldClimateEngine worldEngine = ClimateEngine.getInstance().getClimateEngine(event.getBlock().getWorld().getName());
            double temp = worldEngine.getTemperature();
            double random = GlobalWarming.getInstance().getRandom().nextDouble();
            double chance = distribution.getValue(temp);

            if (chance / 100 <= random) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void setJsonModel(JsonObject jsonModel) {
        super.setJsonModel(jsonModel);
        this.cropDistribution = GlobalWarming.getInstance().getGson().fromJson(jsonModel, new TypeToken<Map<Material, Distribution>>(){}.getType());
        if (cropDistribution == null) {
            unregister();
        }
    }


}