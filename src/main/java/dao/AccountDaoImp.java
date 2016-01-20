package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.AccountEntity;


public class AccountDaoImp implements AccountDao {
	private static final String FINDALL = "SELECT * FROM Account";
	private static final String FIND_BY_ID = "SELECT * FROM Account WHERE ID = ?";
	private static final String INSERT = "INSERT INTO Account("
			+ "USER_ACC, USER_PWD, VALUES(?, ?)";
	private static final String UPDATE = "UPDATE Account SET "
			+ "USER_ACC = ?, USER_PWD = ?, WHERE ID = ?";
	private DataSource dataSource = null;
	
	public AccountDaoImp() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/IIIBR");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<AccountEntity> findAll() {
		List<AccountEntity> accounts = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(AccountDaoImp.FINDALL);
			rs = pstmt.executeQuery();

			accounts = new ArrayList<AccountEntity>();
			while (rs.next()) {
				AccountEntity account = new AccountEntity();
				account.setId(rs.getLong("ID"));
				account.setUserAcc(rs.getString("USER_ACC"));
				account.setUserPwd(rs.getString("USER_PWD"));
				accounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return accounts;
	}

	@Override
	public AccountEntity findById(Long id) {
		AccountEntity account = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(AccountDaoImp.FIND_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				account = new AccountEntity();
				account.setId(rs.getLong("ID"));
				account.setUserAcc(rs.getString("USER_ACC"));
				account.setUserPwd(rs.getString("USER_PWD"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return account;
	}

	@Override
	public Boolean insert(AccountEntity accountEntity) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(AccountDaoImp.INSERT);
			pstmt.setString(1, accountEntity.getUserAcc());
			pstmt.setString(2, accountEntity.getUserPwd());

			if (pstmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	@Override
	public Boolean update(AccountEntity accountEntity) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(AccountDaoImp.UPDATE);

			pstmt.setString(1, accountEntity.getUserAcc());
			pstmt.setString(2, accountEntity.getUserPwd());
			pstmt.setLong(3, accountEntity.getId());
			if (pstmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
