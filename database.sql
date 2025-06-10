-- Departments (Đơn vị)
CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             code VARCHAR(20) UNIQUE NOT NULL,
                             name VARCHAR(100) NOT NULL,
                             description TEXT,
                             parent_id BIGINT REFERENCES departments(id),
                             manager_id BIGINT REFERENCES users(id), -- ✅ Reference đến users
                             budget_limit DECIMAL(15,2),
                             spent_amount DECIMAL(15,2) DEFAULT 0,
                             status VARCHAR(20) DEFAULT 'ACTIVE',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cost Categories (Phân loại chi phí)
CREATE TABLE cost_categories (
                                 id BIGSERIAL PRIMARY KEY,
                                 code VARCHAR(20) UNIQUE NOT NULL,
                                 name VARCHAR(100) NOT NULL,
                                 description TEXT,
                                 requires_advisory BOOLEAN DEFAULT false,
                                 requires_tckh_review BOOLEAN DEFAULT false
);

-- Cost Approval Requests (Đề xuất chi phí)
CREATE TABLE cost_approval_requests (
                                        id BIGSERIAL PRIMARY KEY,
                                        request_number VARCHAR(20) UNIQUE NOT NULL,
                                        submitter_id BIGINT NOT NULL REFERENCES users(id),
                                        department_id BIGINT NOT NULL REFERENCES departments(id),
                                        cost_category_id BIGINT NOT NULL REFERENCES cost_categories(id),
                                        requested_amount DECIMAL(15,2) NOT NULL,
                                        approved_amount DECIMAL(15,2),
                                        description TEXT NOT NULL,
                                        purpose TEXT NOT NULL,
                                        budget_type VARCHAR(20) NOT NULL, -- WITHIN_BUDGET, OUTSIDE_BUDGET
                                        status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Approval Tasks (Tác vụ phê duyệt)
CREATE TABLE approval_tasks (
                                id BIGSERIAL PRIMARY KEY,
                                request_id BIGINT NOT NULL REFERENCES cost_approval_requests(id),
                                task_type VARCHAR(30) NOT NULL, -- UNIT_HEAD, ADVISORY, DEPT_HEAD, FINANCIAL, FINAL
                                assignee_id BIGINT NOT NULL REFERENCES users(id),
                                status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                                decision VARCHAR(20), -- APPROVE, REJECT, REQUEST_MORE_INFO
                                comments TEXT,
                                assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                completed_date TIMESTAMP
);

-- Supporting Documents (Hồ sơ đính kèm)
CREATE TABLE supporting_documents (
                                      id BIGSERIAL PRIMARY KEY,
                                      request_id BIGINT NOT NULL REFERENCES cost_approval_requests(id),
                                      file_name VARCHAR(255) NOT NULL,
                                      file_path VARCHAR(500) NOT NULL,
                                      file_size BIGINT NOT NULL,
                                      uploaded_by BIGINT NOT NULL REFERENCES users(id),
                                      uploaded_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);