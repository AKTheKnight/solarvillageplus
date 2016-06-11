package com.aktheknight.solarvillageplus.blocks;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import com.aktheknight.solarvillageplus.blocks.tiles.SolarPanelContainer;
import com.aktheknight.solarvillageplus.blocks.tiles.TileEntitySolarPanel;
import com.aktheknight.solarvillageplus.util.IBlockRenderer;
import com.aktheknight.solarvillageplus.util.PanelTier;
import com.aktheknight.solarvillageplus.util.Platform;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class SolarPanel extends Block implements ITileEntityProvider,IBlockRenderer {

    protected long capacity;
    protected int gen;
    protected String resourcePath;
    private PanelTier tier;

    protected static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
    
    public SolarPanel(PanelTier tier) {
        //TODO config
        //super(WATER DAMAGE ? Material.CIRCUITS : Material.IRON);
        super(Material.IRON);
        this.resourcePath="panel/"+tier;
        this.tier=tier;
        this.isBlockContainer = true;
        this.setUnlocalizedName(SolarVillagePlus.MODID+".panel_" + tier);
        this.setHardness(0.2F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(SolarVillagePlus.solarvillageplusTab);
        this.setLightOpacity(0);
    }
    
    @Override
    public boolean onBlockActivated (World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        
        if (!worldIn.isRemote) {
            
            final TileEntity tile = worldIn.getTileEntity(pos);
            
            if (tile instanceof TileEntitySolarPanel && !tile.isInvalid()) {
                
                final TileEntitySolarPanel panel = (TileEntitySolarPanel) tile;
                final SolarPanelContainer container = (SolarPanelContainer) panel.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
                playerIn.addChatMessage(new TextComponentString(I18n.format("message.solarvillageplus.panel.status", container.getStoredPower(), container.getCapacity(), container.getGen())));
            }
        }
        
        return true;
    }
    
    @Override
    public void breakBlock (World worldIn, BlockPos pos, IBlockState state) {
        
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
    
    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }
    
    @Override
    public TileEntity createNewTileEntity (World worldIn, int meta) {
        return new TileEntitySolarPanel(tier.getCapacity(), tier.getGen());
    }
    
    @Override
    public AxisAlignedBB getBoundingBox (IBlockState state, IBlockAccess source, BlockPos pos) {
        
        return BOUNDS;
    }
    
    @Override
    public boolean isFullCube (IBlockState state) {
        
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered (IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        
        return side == EnumFacing.UP ? true : blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
    
    @Override
    public boolean doesSideBlockRendering (IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        
        return face == EnumFacing.DOWN;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", SolarVillagePlus.MODID, this.resourcePath);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        final String resourcePath = String.format("%s:%s", SolarVillagePlus.MODID, this.resourcePath);

        List<ItemStack> subBlocks = new ArrayList<ItemStack>();
        getSubBlocks(Item.getItemFromBlock(this), null, subBlocks);

        for (ItemStack itemStack : subBlocks) {
            IBlockState blockState = this.getStateFromMeta(itemStack.getItemDamage());

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), itemStack.getItemDamage(), new ModelResourceLocation(resourcePath, Platform.getPropertyString(blockState.getProperties())));

        }
    }
}