package dao;

import java.util.List;
import models.TinTuc;

public interface TinTucDAO {
	public TinTuc themTinTuc (TinTuc tt);
	public TinTuc capNhatTinTuc(TinTuc tt);
	public boolean xoaTinTuc (int ma);
	public TinTuc timTinTucTheoMa (int ma);
	public List<TinTuc> layDanhSachTinTuc();
}
