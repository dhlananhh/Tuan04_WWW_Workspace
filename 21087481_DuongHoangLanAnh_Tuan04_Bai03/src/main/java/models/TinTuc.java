package models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table (name = "tintuc")
public class TinTuc {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "maTinTuc")
	private int maTinTuc;
	
	@Column (name = "tieuDe")
	@NotNull (message = "Tiêu đề không được rỗng")
	@NotEmpty (message = "Tiêu đề không được rỗng")
	@Size (max = 255, message = "Tiêu đề phải có độ dài không quá 255 ký tự")
	private String tieuDe;
	
	@Column (name = "noiDung")
	@NotNull (message = "Nội dung không được rỗng")
	@NotEmpty (message = "Nội dung không được rỗng")
	@Size (max = 65535, message = "Nội dung phải có độ dài không quá 65535 ký tự")
	private String noiDung;
	
	@Column (name = "lienKet")
	@NotNull (message = "Liên kết không được rỗng")	
	@NotEmpty (message = "Liên kết không được rỗng")
	@Size (max = 255, message = "Liên kết phải có độ dài không quá 255 ký tự")
	private String lienKet;
	
	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "maDanhMuc")
	private DanhMuc danhMuc;

	
	public TinTuc() {
		
	}


	public TinTuc(int maTinTuc, String tieuDe, String noiDung, String lienKet, DanhMuc danhMuc) {
		super();
		this.maTinTuc = maTinTuc;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.lienKet = lienKet;
		this.danhMuc = danhMuc;
	}


	public TinTuc(String tieuDe, String noiDung, String lienKet, DanhMuc danhMuc) {
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.lienKet = lienKet;
		this.danhMuc = danhMuc;
	}


	public int getMaTinTuc() {
		return maTinTuc;
	}


	public void setMaTinTuc(int maTinTuc) {
		this.maTinTuc = maTinTuc;
	}


	public String getTieuDe() {
		return tieuDe;
	}


	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}


	public String getNoiDung() {
		return noiDung;
	}


	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}


	public String getLienKet() {
		return lienKet;
	}


	public void setLienKet(String lienKet) {
		this.lienKet = lienKet;
	}


	public DanhMuc getDanhMuc() {
		return danhMuc;
	}


	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}


	@Override
	public String toString() {
		return "TinTuc [maTinTuc=" + maTinTuc + ", tieuDe=" + tieuDe + ", noiDung=" + noiDung + ", lienKet=" + lienKet
				+ ", danhMuc=" + danhMuc + "]";
	}
	
	
	
}
