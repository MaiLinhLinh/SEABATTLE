# Sea Battle

Sea Battle là một trò chơi dành cho hai người chơi. Trò chơi được chơi trên bốn lưới, hai cho mỗi người chơi. Các lưới thường là vuông - thường là 10 × 10 - và các ô riêng lẻ trong lưới được xác định bằng chữ cái và số. Trên một lưới, người chơi sắp xếp các tàu và ghi lại các phát bắn của đối thủ. Trên lưới còn lại, người chơi ghi lại các phát bắn của riêng mình.



### Cấu trúc folder

```bash

├── branch develop
│   ├── Văn Thị Mai Linh
│   │   ├── src
│   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── Main.java
│   │   │   │   │   │   ├── Game.java
│   │   │   │   │   │   ├── Player.java
│   │   │   │   │   ├── model
│   │   │   │   │   │   ├── Ship.java
│   │   │   │   │   │   ├── Board.jave

```




## Requirements, User Stories

### User Story 
- Hân là một sinh viên trẻ tài năng cùng những ý tưởng đầy táo bạo. Cô nhận thấy trong lớp đang thịnh hành một trò chơi sử dụng giấy, tức là ta sẽ gập tờ giấy làm đôi, vẽ hai bảng ở 2 mặt và hai người sẽ đánh các vật thể - thuyền trên các ô trên mặt của mình. Hai bên không biết vị trí tàu của bên kia và sẽ vẽ bắn thử ở 1 mặt, rồi sau đó gập giấy lại xem điểm bắn đó có trùng với ô nào mặt đối thủ không, và phá huỷ vị trí đó. ☄️

- 👾 Quy trình chơi game này khá dài dòng và khó hiểu, hơn nữa còn tạo ra nhiều giấy rác trong lớp. Sau vài ngày suy nghĩ, cô đã vẽ ra được những ý tưởng sơ thảo cho một phần mềm, hứa hẹn giải quyết bài toán này.


---
### Yêu cầu sản phẩm
- Là một người chơi, tôi có thể vào game và bắt đầu chơi. Sẽ có hai người chơi trong một ván game và lượt sẽ được luân phiên.

#### 📝 Vòng chuẩn bị
- Ban đầu, người chơi được cho 1 bảng 10x10, một cột đánh từ A->J, một cột đánh từ 1->10. Mỗi người chơi sở hữu 5 con thuyền như sau:
    - 2 Thuyền Tuần Tra (Patrol Boat) 1x2
    - 1 Tàu Khu Trục (Destroyer Boat) 1x4
    - 1 Tàu Ngầm (Submarine) 1x3
    - 1 Thiết Giáp Hạm (Battle Ship) 1x5
![Alt text](image.png)

- Người chơi sẽ có thể nhập vào 2 toạ độ (X,Y) với từng mẫu thuyền để đặt thuyền, màn hình sẽ hiển thị thuyền lên bảng. Sau khi đặt xong hết, sẽ sang lượt đặt của người kia.

---
#### 📝 Trong Game
- Trong trò chơi, lượt của hai người chơi sẽ luân phiên nhau, và người bắn trúng có thể được bắn tiếp, người kia sẽ được chơi nếu người chơi hiện tại bắn trượt. Trong lượt của 1 người, anh có thể chọn các lựa chọn sau:
- Khi hiển thị menu lựa chọn, hiển thị luôn tình hình hiện tại: gồm số ô đã bắn ở mặt trận địch, số tàu đã phá, số tàu còn lại của bản thân.
    - Xem bảng, cách đặt thuyền của bản thân (Nếu hai người cùng chơi trên 1 máy tính, lúc này anh sẽ tạm quay máy tính đi để xem cục diện hiện tại)
    - Đặt lệnh khai hoả, có thể nhập 1 toạ độ X,Y để tiến hành khai hoả vào mặt trận đối phương, nếu trúng bất kì điểm nào của thuyền thì sẽ có thông báo, còn không trúng thì thôi. Người chơi có thể xem được bảng của đối phương ở dạng sương mù (tức là hiển thị những điểm nào đã bị bắn vào rồi, bao gồm điểm nào đã bắn vào nhưng không có gì, điểm nào đã bắn vào nhưng có một phần tàu ở đó, ...) các ô khác thì không hiển thị thông tin gì
    - Kết thúc lượt

---
#### 📝 Luật trò chơi
- 1 tàu sẽ bị phá huỷ chỉ sau khi toàn bộ điểm bị phá hết. ví dụ tàu 1x5 thì ít nhất 5 phát bắn trúng đích toàn bộ để phá
- Khi bên nào bị phá hết tàu trước lập tức thua cuộc và - hiển thị màn hình kết quả, bảng của cả 2 bên

