package team.creative.littletiles.common.placement;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import team.creative.creativecore.common.util.math.base.Facing;
import team.creative.littletiles.common.grid.LittleGrid;
import team.creative.littletiles.common.math.vec.LittleVec;
import team.creative.littletiles.common.math.vec.LittleVecAbsolute;
import team.creative.littletiles.common.math.vec.LittleVecGrid;

public class PlacementPosition extends LittleVecAbsolute {
    
    public final Facing facing;
    
    public PlacementPosition(BlockPos pos, LittleVecGrid vec, Facing facing) {
        super(pos, vec);
        this.facing = facing;
    }
    
    public PlacementPosition(BlockPos pos, LittleGrid context, LittleVec vec, Facing facing) {
        super(pos, context, vec);
        this.facing = facing;
    }
    
    public PlacementPosition(BlockHitResult result, LittleGrid context) {
        super(result, context);
        this.facing = Facing.get(result.getDirection());
    }
    
    public void assign(LittleVecAbsolute pos) {
        this.pos = pos.getPos();
        this.gridVec = pos.getVecGrid();
    }
    
    public void subVec(LittleVec vec) {
        getVec().add(vec);
        removeInternalBlockOffset();
    }
    
    public void addVec(LittleVec vec) {
        getVec().sub(vec);
        removeInternalBlockOffset();
    }
    
    @Override
    public PlacementPosition copy() {
        return new PlacementPosition(pos, gridVec.copy(), facing);
    }
    
    public AABB getBox() {
        double x = getPosX();
        double y = getPosY();
        double z = getPosZ();
        return new AABB(x, y, z, x + getGrid().pixelLength, y + getGrid().pixelLength, z + getGrid().pixelLength);
    }
}
