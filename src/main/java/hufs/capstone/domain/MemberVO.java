package hufs.capstone.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @Entity
public class MemberVO {
    @Id
    @Column(name = "id", length = 20, unique = true)
    private int id;  //고유 번호
    @Column(name = "username", nullable = false)
    private String username;  //이름
    @Column(name = "addr", nullable = false)
    private String addr; //주소
    @Column(name = "phone", unique = true)
    private String phone; //전화번호
    @Column(name = "mail", unique = true)
    private String mail; //메일 주소
    @Column(name = "gender", nullable = false)
    private int gender; //남자 0, 여자1
    @Column(name = "nick", unique = true)
    private String nick; //닉네임
    @Column(name = "point", nullable = false)
    private int point; //포인트
    @Column(name = "mScore", nullable = false)
    private int mScore; //매너점수
    @Column(name = "age", nullable = false)
    private int age; //나이
    @Column(name = "sName", nullable = false)
    private String sName; //학교 이름
    @Column(name = "major", nullable = false)
    private String major; //전공
    @Column(name = "sId", unique = true)
    private int sId; //학번
    @Column(name = "password", nullable = false)
    private String password; //비밀번호

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
