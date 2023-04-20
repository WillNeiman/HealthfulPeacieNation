package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.QuestionDTO;
import util.DbUtil;

public class QuestionRepositoryImpl implements QuestionRepository {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	// 寃���湲� ���� ��
	public void save(QuestionDTO questionDTO) {
		System.out.println("QuestionRepositoryImpl - save");
		String sql = "insert into questions (question_id, user_id, category, goods_id, title, content) values (questions_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionDTO.getUserId());
			pstmt.setString(2, questionDTO.getCategory());
			pstmt.setLong(3, questionDTO.getGoodsId());
			pstmt.setString(4, questionDTO.getTitle());
			pstmt.setString(5, questionDTO.getContent());
			System.out.println("questionDTO.getUserId(): " + questionDTO.getUserId());
			System.out.println("questionDTO.getContent(): " + questionDTO.getCategory());
			System.out.println("questionDTO.getGoodsId(): " + questionDTO.getGoodsId());
			System.out.println("questionDTO.getTitle(): " + questionDTO.getTitle());
			System.out.println("questionDTO.getContent(): " + questionDTO.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	// 湲�踰��몃� 議고��(���몃낫湲�)
	public QuestionDTO findById(long questionId) {
		System.out.println("QuestionRepositoryImpl - findById");
		String sql = "SELECT * FROM questions where question_id = ?";
		QuestionDTO question = new QuestionDTO();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, questionId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				question.setQuestionId(rs.getLong("question_id"));
				question.setUserId(rs.getString("user_id"));
				question.setCategory(rs.getString("category"));
				question.setGoodsId(rs.getLong("goods_id"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setCreatedAt(rs.getTimestamp("question_created_at"));
				question.setUpdatedAt(rs.getTimestamp("reply_created_at"));

				String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }	            
	            question.setUpdatedAt(rs.getTimestamp("reply_created_at"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return question;
	}

	// ���곗�댄��
	public void update(QuestionDTO questionDTO) {
		System.out.println("QuestionRepositoryImpl - update");

		String sql = "UPDATE questions SET category = ?, goods_id = ?, title = ?, content = ?, reply = ?, reply_created_at = ? WHERE question_id = ?";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionDTO.getCategory());
			pstmt.setLong(2, questionDTO.getGoodsId());
			pstmt.setString(3, questionDTO.getTitle());
			pstmt.setString(4, questionDTO.getContent());
			pstmt.setString(5, questionDTO.getReply());
			pstmt.setTimestamp(6, questionDTO.getUpdatedAt());
			pstmt.setLong(7, questionDTO.getQuestionId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	// 寃���湲� ���� (���� : if( �듦� != null) 愿�由ъ�� : 議곌굔 X) ��
	public void delete(Long questionId) {
		System.out.println("QuestionRepositoryImpl - delete");
		String sql = "DELETE FROM questions WHERE question_id = ?";
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, questionId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	// ��泥닿� 議고��
	public List<QuestionDTO> findAll() {
		System.out.println("QuestionRepositoryImpl - findAll");
		String sql = "SELECT * FROM questions order by question_id desc";
		ArrayList<QuestionDTO> questionList = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setQuestionId(rs.getLong("question_id"));
				question.setUserId(rs.getString("user_id"));
				question.setCategory(rs.getString("category"));
				question.setGoodsId(rs.getLong("goods_id"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setCreatedAt(rs.getTimestamp("question_created_at"));
				question.setUpdatedAt(rs.getTimestamp("reply_created_at"));

				String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }
	            
	            question.setUpdatedAt(rs.getTimestamp("reply_created_at"));
				
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return questionList;
	}

	// getAllcount()
	public int getAllCount() {
		System.out.println("QuestionRepositoryImpl - getAllcount");
		int count = 0;
		try {
			this.conn = DbUtil.getConnection();
			String sql = "select count(*) from questions";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return count;
	}	

	@Override
	public List<QuestionDTO> getListByRange(int startRow, int endRow) {
	    System.out.println("QuestionRepositoryImpl - getListByRange");
		List<QuestionDTO> questions = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM questions ORDER BY question_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setQuestionId(rs.getLong("question_id"));
				System.out.println("question_id: " + rs.getLong("question_id"));
				question.setUserId(rs.getString("user_id"));
				question.setCategory(rs.getString("category"));
				question.setGoodsId(rs.getLong("goods_id"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setCreatedAt(rs.getTimestamp("question_created_at"));
				String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }
				question.setUpdatedAt(rs.getTimestamp("reply_created_at"));
				questions.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return questions;
	}

	@Override
	public List<QuestionDTO> findById(String userId) {
		System.out.println("QuestionRepositoryImpl - findById");
		String sql = "SELECT * FROM questions where user_id=? order by question_id desc";
		ArrayList<QuestionDTO> questionList = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int questionId = rs.getInt("question_id");
				String category = rs.getString("category");
				long goodsId = rs.getLong("goods_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp questionCreatedAt = rs.getTimestamp("question_created_at");
				Timestamp replyCreatedAt = rs.getTimestamp("reply_created_at");				
				String reply = rs.getString("reply");
	            if(reply != null) {
	            	reply = (reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	            	reply = "";
	            }
				QuestionDTO question = new QuestionDTO(questionId, userId, category, goodsId, title, content,
						questionCreatedAt, reply, replyCreatedAt);
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return questionList;
	}

	@Override
	public int getAllCountById(String userId) {
		System.out.println("QuestionRepositoryImpl - getAllCountById");
		int count = 0;
		try {
			this.conn = DbUtil.getConnection();
			String sql = "select count(*) from questions where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return count;
	}

	@Override
	public List<QuestionDTO> findByGoodsId(Long goodsId) {
	    System.out.println("QuestionRepositoryImpl - findByGoodsId");
	    String sql = "SELECT * FROM questions where goods_id = ? order by question_created_at desc";
	    ArrayList<QuestionDTO> questionList = new ArrayList<>();
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, goodsId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            QuestionDTO question = new QuestionDTO();
	            question.setQuestionId(rs.getInt("question_id"));
	            question.setUserId(rs.getString("user_id"));
	            question.setCategory(rs.getString("category"));
	            question.setGoodsId(goodsId);
	            question.setTitle(rs.getString("title"));
	            question.setContent(rs.getString("content"));
	            question.setCreatedAt(rs.getTimestamp("question_created_at"));
	            
	            String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }
	            
	            question.setUpdatedAt(rs.getTimestamp("reply_created_at"));

	            questionList.add(question);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	    return questionList;
	}

	@Override
	public List<QuestionDTO> findTop5RecentQuestionsByGoodsId(Long goodsId) {
		System.out.println("QuestionRepositoryImpl - findTop5RecentQuestionsByGoodsId");
		String sql = "SELECT * FROM (SELECT * FROM questions WHERE goods_id = ? ORDER BY question_created_at DESC) WHERE ROWNUM <= 5";
		ArrayList<QuestionDTO> questionList = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, goodsId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setQuestionId(rs.getInt("question_id"));
				question.setUserId(rs.getString("user_id"));
				question.setCategory(rs.getString("category"));
				question.setGoodsId(rs.getLong("goods_id"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setCreatedAt(rs.getTimestamp("question_created_at"));
				question.setReply(rs.getString("reply"));				
				String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }	            
	            question.setUpdatedAt(rs.getTimestamp("reply_created_at"));
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return questionList;
	}
	
	// �듦� ���곗�댄��
	@Override
	public void updateReply(long questionId, String reply, Timestamp replyCreatedAt) {
	    System.out.println("QuestionRepositoryImpl - updateReply");
	    String sql = "UPDATE questions SET reply = ?, reply_created_at = ? WHERE question_id = ?";
	    try {
			this.conn = DbUtil.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, reply);
	        pstmt.setTimestamp(2, replyCreatedAt);
	        pstmt.setLong(3, questionId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}
	
	@Override
	public List<QuestionDTO> findByUserIdWithPagination(String userId, int startRow, int endRow) {
	    System.out.println("QuestionRepositoryImpl - findByUserIdWithPagination");
		List<QuestionDTO> questions = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM questions WHERE user_id LIKE ? ORDER BY question_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + userId + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setQuestionId(rs.getLong("question_id"));
				question.setUserId(rs.getString("user_id"));
				question.setCategory(rs.getString("category"));
				question.setGoodsId(rs.getLong("goods_id"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setCreatedAt(rs.getTimestamp("question_created_at"));
				String reply = rs.getString("reply");
	            if(reply != null) {
	                question.setReply(reply.replace(System.lineSeparator(), "<br>"));
	            } else {
	                question.setReply("");
	            }
				question.setUpdatedAt(rs.getTimestamp("reply_created_at"));
				questions.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return questions;
	}

}
