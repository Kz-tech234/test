import React, { useState, useEffect } from "react";
import { Button, Table, Form, Alert } from "react-bootstrap";
import { authApis } from "../../config/Apis";

const XepDeTai = () => {
  const [khoaHocList, setKhoaHocList] = useState([]);
  const [selectedKhoaHoc, setSelectedKhoaHoc] = useState("");
  const [sinhVienList, setSinhVienList] = useState([]);
  const [deTaiList, setDeTaiList] = useState([]);
  const [phanCongList, setPhanCongList] = useState([]); // danh sách phân công đề tài cho sv
  const [msg, setMsg] = useState("");

  // 1. Load danh sách khóa học
  useEffect(() => {
    const loadKhoaHoc = async () => {
      try {
        const res = await authApis().get("/giaovu/khoahoc");
        setKhoaHocList(res.data);
        if (res.data.length > 0) setSelectedKhoaHoc(res.data[0]);
      } catch (error) {
        setMsg("Lỗi tải danh sách khóa học: " + error.message);
      }
    };
    loadKhoaHoc();
  }, []);

  // 2. Load danh sách đề tài (đề tài sẵn có)
  useEffect(() => {
    const loadDeTai = async () => {
      try {
        const res = await authApis().get("/detai");
        setDeTaiList(res.data);
      } catch (error) {
        setMsg("Lỗi tải danh sách đề tài: " + error.message);
      }
    };
    loadDeTai();
  }, []);

  // 3. Load danh sách sinh viên theo khóa học
  const loadSinhVien = async (khoa) => {
    try {
      const res = await authApis().get(`/giaovu/sinhvien?khoaHoc=${khoa}`);
      setSinhVienList(res.data);
      setMsg("");
    } catch (error) {
      setMsg("Lỗi tải danh sách sinh viên: " + error.message);
    }
  };

  // 4. Xử lý thay đổi chọn khóa
  const handleKhoaChange = (e) => {
    setSelectedKhoaHoc(e.target.value);
    setSinhVienList([]);
    setPhanCongList([]);
  };

  // 5. Xử lý khi bấm lọc danh sách sinh viên
  const handleLocDanhSach = () => {
    if (!selectedKhoaHoc) {
      setMsg("Vui lòng chọn khóa học");
      return;
    }
    loadSinhVien(selectedKhoaHoc);
  };

  // 6. Hàm xếp đề tài ngẫu nhiên cho sinh viên
  const handleXepDeTai = () => {
    if (sinhVienList.length === 0) {
      setMsg("Chưa có sinh viên để xếp đề tài");
      return;
    }
    if (deTaiList.length === 0) {
      setMsg("Chưa có đề tài để xếp");
      return;
    }

    let assignments = [];
    const nDeTai = deTaiList.length;
    const nSinhVien = sinhVienList.length;

    // Gán đề tài ngẫu nhiên cho sinh viên
    for (let i = 0; i < nSinhVien; i++) {
      // VD: vòng quanh đề tài nếu ít đề tài hơn sv
      const deTaiIndex = i % nDeTai;
      assignments.push({
        sinhVienId: sinhVienList[i].id,  // chỉnh theo trường id sinh viên backend trả về
        deTaiId: deTaiList[deTaiIndex].id, // chỉnh theo id đề tài
      });
    }

    setPhanCongList(assignments);
    setMsg("Đã xếp đề tài ngẫu nhiên cho sinh viên.");
  };

  // 7. Gửi danh sách phân công lên backend
  const handleLuuPhanCong = async () => {
    if (phanCongList.length === 0) {
      setMsg("Chưa có phân công đề tài để lưu");
      return;
    }

    try {
      // Giả sử backend có API nhận phân công: POST /giaovu/phancongdetai
      // Dữ liệu gửi là danh sách mảng { sinhVienId, deTaiId }
      await authApis().post("/giaovu/phancongdetai", phanCongList);
      setMsg("Lưu phân công đề tài thành công");
    } catch (error) {
      setMsg("Lỗi lưu phân công đề tài: " + error.message);
    }
  };

  return (
    <div className="container mt-3">
      <h3 className="text-primary mb-3">Xếp danh sách sinh viên thực hiện khóa luận</h3>

      {msg && <Alert variant="info">{msg}</Alert>}

      <Form.Group className="mb-3" controlId="khoaHocSelect">
        <Form.Label>Chọn khóa</Form.Label>
        <Form.Select value={selectedKhoaHoc} onChange={handleKhoaChange}>
          <option value="">-- Chọn khóa --</option>
          {khoaHocList.map((khoa) => (
            <option key={khoa} value={khoa}>
              Khóa {khoa}
            </option>
          ))}
        </Form.Select>
      </Form.Group>

      <Button variant="primary" className="mb-3" onClick={handleLocDanhSach}>
        Lọc danh sách
      </Button>

      <h5>
        Danh sách sinh viên thuộc khoa Công nghệ thông tin - Khóa {selectedKhoaHoc}:
      </h5>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Tên đăng nhập</th>
            <th>Email</th>
            <th>Đề tài được xếp</th>
          </tr>
        </thead>
        <tbody>
          {sinhVienList.length > 0 ? (
            sinhVienList.map((sv, idx) => {
              const assignment = phanCongList.find(
                (p) => p.sinhVienId === sv.id
              );
              const deTai = assignment
                ? deTaiList.find((dt) => dt.id === assignment.deTaiId)
                : null;

              return (
                <tr key={sv.id}>
                  <td>{idx + 1}</td>
                  <td>{sv.username}</td>
                  <td>{sv.email}</td>
                  <td>{deTai ? deTai.tenDeTai : "-"}</td>
                </tr>
              );
            })
          ) : (
            <tr>
              <td colSpan="4" className="text-center">
                Chưa có sinh viên nào
              </td>
            </tr>
          )}
        </tbody>
      </Table>

      <Button variant="success" className="me-2" onClick={handleXepDeTai}>
        Xếp đề tài ngẫu nhiên
      </Button>

      <Button variant="primary" onClick={handleLuuPhanCong}>
        Lưu phân công đề tài
      </Button>
    </div>
  );
};

export default XepDeTai;
