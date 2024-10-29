package dao;


import java.util.List;
import models.DanhMuc;

public interface DanhMucDAO {
	public DanhMuc themDanhMuc (DanhMuc dm);
	public DanhMuc capNhatDanhMuc (DanhMuc dm);
	public boolean xoaDanhMuc (int ma);
	public List<DanhMuc> layDanhSachDanhMuc();
	public DanhMuc timDanhMucTheoMa (int ma);
}
