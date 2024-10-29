package models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table (name = "danhmuc")
public class DanhMuc {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "maDanhMuc")
	@NotNull (message = "Mã danh mục không được rỗng")
	@NotEmpty (message = "Mã danh mục không được để trống")
	private int maDanhMuc;
	

	@Column (name = "tenDanhMuc")
	@NotNull (message = "Tên danh mục không được rỗng")
	@NotEmpty (message = "Tên danh mục không được để trống")
	@Size (max = 255, message = "Tên danh mục phải có độ dài không quá 255 ký tự")
	private String tenDanhMuc;
	
	
	@Column (name = "nguoiQuanLy")
	@NotNull (message = "Người quản lý không được rỗng")
	@NotEmpty (message = "Người quản lý không được để trống")
	@Size (max = 255, message = "Người quản lý phải có độ dài không quá 255 ký tự")
	private String nguoiQuanLy;
	
	@Column (name = "ghiChu")
	@NotNull (message = "Ghi chú không được rỗng")
	@NotEmpty (message = "Ghi chú không được để trống")
	@Size (max = 65535, message = "Ghi chú phải có độ dài không quá 65535 ký tự")
	private String ghiChu;
	
	@OneToMany (mappedBy = "danhMuc")
	private List<TinTuc> danhSachTinTuc;

	
	public DanhMuc() {

	}
	
	
	public DanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}


	public DanhMuc(int maDanhMuc, String tenDanhMuc) {
		this.maDanhMuc = maDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
	}


	public DanhMuc (int maDanhMuc, String tenDanhMuc, String nguoiQuanLy, String ghiChu) {
		this.maDanhMuc = maDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.nguoiQuanLy = nguoiQuanLy;
		this.ghiChu = ghiChu;
	}


	public DanhMuc (String tenDanhMuc, String nguoiQuanLy, String ghiChu) {
		this.tenDanhMuc = tenDanhMuc;
		this.nguoiQuanLy = nguoiQuanLy;
		this.ghiChu = ghiChu;
	}


	public int getMaDanhMuc() {
		return maDanhMuc;
	}


	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}


	public String getTenDanhMuc() {
		return tenDanhMuc;
	}


	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}


	public String getNguoiQuanLy() {
		return nguoiQuanLy;
	}


	public void setNguoiQuanLy(String nguoiQuanLy) {
		this.nguoiQuanLy = nguoiQuanLy;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	@Override
	public String toString() {
		return String.format("DanhMuc [maDanhMuc = %d, tenDanhMuc = %s, nguoiQuanLy = %s, ghiChu = %s]", 
				maDanhMuc, tenDanhMuc, nguoiQuanLy, ghiChu);
	}
	
}
