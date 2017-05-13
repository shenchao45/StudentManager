package com.shenchao.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.hibernate.sql.Update;import org.springframework.dao.support.DaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shenchao.entity.Course;
import com.shenchao.entity.Student;
import com.shenchao.entity.StudentCourse;
import com.shenchao.repository.CourseRepository;
import com.shenchao.repository.StudentCourseRepository;
import com.shenchao.repository.StudentRepository;
import com.shenchao.util.Utils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

interface CallBack{
	void run();
}

public class MainUI extends JFrame implements CallBack {

	public static LoginDialog loginDialog;
	private static StudentRepository studentRepository = Utils.getBean(StudentRepository.class);
	private static CourseRepository courseRepository = Utils.getBean(CourseRepository.class);
	private static StudentCourseRepository studentCourseRepository = Utils.getBean(StudentCourseRepository.class);
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JTextField tfScore;
	private JTable table_2;
	private JLabel label_1;
	private JTextField tfN;
	private JLabel label_2;
	private JTextField tfC;
	private JLabel label_3;
	private JTextField tfCN;
	private JButton button_1;
	private JLabel label_4;
	private JLabel lbXH;
	private JLabel label_6;
	private JLabel lbName;
	private JLabel label_8;
	private JLabel lbCompoent;
	private JLabel lblChengji;
	private static String[] studentColums = {"ID","学号", "姓名"};
	private static Object[][] studentDatas = {{"","14200135111","沈超"}};
	private static String[] courseColums = {"ID","课程名称"};
	private static Object[][] courseDatas = {{"","离散数学"}};
	private static String[] scoreColums = {"ID","学号", "姓名","班级","课程名称","成绩"};
	private static Object[][] scoreDatas = {{"","14200135111","沈超","z1411","计算机组成原理","23"}};
	private List<Student> students;
	private List<Course> courses;
	private List<StudentCourse> studentCourses;
	private JLabel abc;
	private JLabel lbClass;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	private JMenuItem menuItem_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
					frame.updateUI(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateUI(boolean isQueryScore){
		students = studentRepository.findAll();
		courses = courseRepository.findAll();
		if (isQueryScore) {
			studentCourses = studentCourseRepository.findAll();
		}
		if (students!=null&&students.size()>0) {
			studentDatas = new Object[students.size()][2];
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				studentDatas[i] = new Object[]{student.getSnumber(),student.getSnumber()+"",student.getSname()};
			}
		}else{
			studentDatas = null;
		}
		if (courses!=null&&courses.size()>0) {
			courseDatas = new Object[courses.size()][2];
			for (int i = 0; i < courses.size(); i++) {
				Course course = courses.get(i);
				courseDatas[i] = new Object[]{course.getCid()+"",course.getCname()};
			}
		}else{
			courseDatas = null;
		}
		if (studentCourses!=null&&studentCourses.size()>0) {
			scoreDatas = new Object[studentCourses.size()][5];
			for (int i = 0; i < studentCourses.size(); i++) {
				StudentCourse studentCourse = studentCourses.get(i);
				Student student = studentCourse.getStudent();
				Course course = studentCourse.getCourse();
				scoreDatas[i] = new Object[]{studentCourse.getId()+"",student.getSnumber(),student.getSname(),student.getSclass(),course.getCname(),studentCourse.getScore()+""};
			}
		}else{
			scoreDatas = null;
		}
		table.setModel(new DefaultTableModel(
			studentDatas,
			studentColums
		));
		table_1.setModel(new DefaultTableModel(
				courseDatas,
				courseColums
				));
		table_2.setModel(new DefaultTableModel(
				scoreDatas,
				scoreColums
				));
		table.setRowHeight(25);
		table_1.setRowHeight(25);
		table_2.setRowHeight(25);
		TableColumn column = table.getColumnModel().getColumn(0);
		TableColumn column1 = table_1.getColumnModel().getColumn(0);
		TableColumn column2 = table_2.getColumnModel().getColumn(0);
		TableColumn column4 = table_2.getColumnModel().getColumn(4);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, renderer);
		table_1.setDefaultRenderer(Object.class, renderer);
		table_2.setDefaultRenderer(Object.class, renderer);
		table_1.getTableHeader().setPreferredSize(new Dimension(table_1.getWidth(), 30));
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));
		table_1.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));
		table_2.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));
		table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 30));
		table_2.getTableHeader().setPreferredSize(new Dimension(table_2.getWidth(), 30));
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);
		column2.setMinWidth(0);
		column2.setMaxWidth(0);
		column4.setMinWidth(180);
		column4.setMaxWidth(180);
	}
	private AddEditStudentDialog studentDialog;
	/**
	 * Create the frame.
	 */
	public MainUI() {
		setTitle("学生成绩管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 687);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("系统");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("注销");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginDialog==null) {
					loginDialog = new LoginDialog();
				}
				loginDialog.setVisible(true);
				MainUI.this.setVisible(false);
			}
		});
		menu.add(menuItem);
		
		menuItem_1 = new JMenuItem("退出");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("学生管理");
		menuBar.add(menu_1);
		
		menuItem_2 = new JMenuItem("添加学生");
		
		menuItem_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (studentDialog==null) {
					studentDialog = new AddEditStudentDialog();
					studentDialog.setCallBack(MainUI.this);
				}
				studentDialog.setStudent(null);
				studentDialog.setVisible(true);
			}
		});
		menu_1.add(menuItem_2);
		
		menuItem_3 = new JMenuItem("修改学生信息");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentDialog==null) {
					studentDialog = new AddEditStudentDialog();
					studentDialog.setCallBack(MainUI.this);
				}
				studentDialog.setStudent(students.get(table.getSelectedRow()));
				studentDialog.setVisible(true);
			}
		});
		menu_1.add(menuItem_3);
		
		menuItem_4 = new JMenuItem("删除学生");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow()!=-1) {
					Student student = students.get(table.getSelectedRow());
					studentRepository.delete(student);
					JOptionPane.showMessageDialog(MainUI.this, "操作成功","提示",JOptionPane.INFORMATION_MESSAGE);
					updateUI(true);
				}else{
					JOptionPane.showMessageDialog(MainUI.this, "操作失败，没有选中相应的条目","提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menu_1.add(menuItem_4);
		
		JMenu menu_2 = new JMenu("课程管理");
		menuBar.add(menu_2);
		
		menuItem_5 = new JMenuItem("添加课程");
		menuItem_5.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if (dialog==null) {
					dialog = new AddCourseDialog();
				}
				dialog.setCallback(MainUI.this);
				dialog.setVisible(true);
			}
		});
		menu_2.add(menuItem_5);
		
		menuItem_6 = new JMenuItem("删除课程");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow()!=-1) {
					courseRepository.delete(courses.get(table_1.getSelectedRow()));
					Utils.showSuccessDialog(MainUI.this);
					updateUI(true);
				}else{
					Utils.showErrorDialog(MainUI.this);
				}
			}
		});
		menu_2.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 13, 266, 322);
		contentPane.add(scrollPane);
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow()==-1) {
					setStudent(null);
					return;
				}else{
					setStudent(students.get(table.getSelectedRow()));
				}
				updateScore();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "abc"},
			},
			new String[] {
				"学号", "姓名"
			}
		));
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(527, 13, 183, 322);
		contentPane.add(scrollPane_1);
		
		table_1 =  new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_1.setFont(new Font("宋体", Font.PLAIN, 20));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table_1.getSelectedRow()==-1) {
					lbCompoent.setText("");
					return;
				}else{
					lbCompoent.setText(courses.get(table_1.getSelectedRow()).getCname());
				}
				updateScore();
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"离散数学"},
			},
			new String[] {
				"课程名称"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		tfScore = new JTextField();
		tfScore.setFont(new Font("宋体", Font.BOLD, 20));
		tfScore.setBounds(371, 247, 142, 32);
		contentPane.add(tfScore);
		tfScore.setColumns(10);
		
		JButton button = new JButton("打分");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = students.get(table.getSelectedRow());
				Course course = courses.get(table_1.getSelectedRow());
				StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(student, course);
				if (studentCourse==null) {
					studentCourse = new StudentCourse();
					studentCourse.setStudent(student);
					studentCourse.setCourse(course);
				}
				studentCourse.setScore(Integer.parseInt(tfScore.getText()));
				studentCourseRepository.saveAndFlush(studentCourse);
				updateUI(true);
			}
		});
		button.setBounds(293, 308, 113, 27);
		contentPane.add(button);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 432, 700, 200);
		contentPane.add(scrollPane_2);
		
		table_2 =  new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table_2.setFont(new Font("宋体", Font.PLAIN, 20));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"14200135111","沈超","z1411","计算机组成原理","23"}
			},
			new String[] {
				"学号", "姓名","班级","课程名称","成绩"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		label_1 = new JLabel("学号");
		label_1.setBounds(14, 392, 72, 18);
		contentPane.add(label_1);
		
		tfN = new JTextField();
		tfN.setBounds(62, 389, 125, 24);
		contentPane.add(tfN);
		tfN.setColumns(10);
		
		label_2 = new JLabel("班级");
		label_2.setBounds(201, 392, 53, 18);
		contentPane.add(label_2);
		
		tfC = new JTextField();
		tfC.setBounds(241, 389, 104, 24);
		contentPane.add(tfC);
		tfC.setColumns(10);
		
		label_3 = new JLabel("课程名称");
		label_3.setBounds(371, 392, 72, 18);
		contentPane.add(label_3);
		
		tfCN = new JTextField();
		tfCN.setBounds(442, 389, 143, 24);
		contentPane.add(tfCN);
		tfCN.setColumns(10);
		
		button_1 = new JButton("查询");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Specification<StudentCourse> specification = new Specification<StudentCourse>() {
					
					@Override
					public Predicate toPredicate(Root<StudentCourse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						Path<Student> sPath = root.get("student");
						Path<Course> cPath = root.get("course");
						ArrayList<Predicate> predicates = new ArrayList<>();
						if (!StringUtils.isEmpty(tfC.getText())) {
							Predicate like = cb.like(sPath.get("sclass"),"%"+tfC.getText()+"%");
							predicates.add(like);
						}
						if (!StringUtils.isEmpty(tfN.getText())) {
							Predicate predicate = cb.equal(sPath.get("snumber"), tfN.getText());
							predicates.add(predicate);
						}
						if (!StringUtils.isEmpty(tfCN.getText())) {
							predicates.add(cb.equal(cPath.get("cname"), tfCN.getText()));
						}
						Predicate[] result = new Predicate[predicates.size()];
						return cb.and( predicates.toArray(result));
					}
				};
				studentCourses = studentCourseRepository.findAll(specification);
				updateUI(false);
			}
		});
		button_1.setBounds(599, 388, 103, 27);
		contentPane.add(button_1);
		
		label_4 = new JLabel("学号:");
		label_4.setFont(new Font("宋体", Font.BOLD, 18));
		label_4.setBounds(305, 25, 53, 40);
		contentPane.add(label_4);
		
		lbXH = new JLabel("14200135111");
		lbXH.setFont(new Font("宋体", Font.BOLD, 18));
		lbXH.setBounds(373, 25, 122, 40);
		contentPane.add(lbXH);
		
		label_6 = new JLabel("姓名:");
		label_6.setFont(new Font("宋体", Font.BOLD, 18));
		label_6.setBounds(305, 82, 53, 40);
		contentPane.add(label_6);
		
		lbName = new JLabel("沈超");
		lbName.setFont(new Font("宋体", Font.BOLD, 18));
		lbName.setBounds(371, 82, 113, 40);
		contentPane.add(lbName);
		
		label_8 = new JLabel("课程:");
		label_8.setFont(new Font("宋体", Font.BOLD, 18));
		label_8.setBounds(305, 194, 53, 40);
		contentPane.add(label_8);
		
		lbCompoent = new JLabel("计算机组成原理");
		lbCompoent.setFont(new Font("宋体", Font.BOLD, 18));
		lbCompoent.setBounds(371, 194, 142, 40);
		contentPane.add(lbCompoent);
		
		lblChengji = new JLabel("成绩:");
		lblChengji.setFont(new Font("宋体", Font.BOLD, 18));
		lblChengji.setBounds(305, 243, 53, 40);
		contentPane.add(lblChengji);
		
		abc = new JLabel("班级:");
		abc.setFont(new Font("宋体", Font.BOLD, 18));
		abc.setBounds(305, 135, 53, 40);
		contentPane.add(abc);
		
		lbClass = new JLabel("z1411");
		lbClass.setFont(new Font("宋体", Font.BOLD, 18));
		lbClass.setBounds(371, 135, 142, 40);
		contentPane.add(lbClass);
	}
	
	public void updateScore(){
		if (table.getSelectedRow()!=-1&&table_1.getSelectedRow()!=-1) {
			StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(students.get(table.getSelectedRow()), courses.get(table_1.getSelectedRow()));
			if (studentCourse!=null) {
				tfScore.setText(studentCourse.getScore()+"");
			}else{
				tfScore.setText("");
			}
		}else{
			tfScore.setText("");
		}
	}
	
	public void setStudent(Student student){
		if (student==null) {
			lbName.setText("");
			lbXH.setText("");
			lbClass.setText("");
			lblChengji.setText("");
		}else{
			lbName.setText(student.getSname());
			lbXH.setText(student.getSnumber());
			lbClass.setText(student.getSclass());
		}
	}
	private AddCourseDialog dialog;
	@Override
	public void run() {
		updateUI(true);
	}
}
