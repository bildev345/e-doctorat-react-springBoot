@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepo;
    private final CandidatRepository candidatRepo;
    private final SujetRepository sujetRepo;
    private final CommissionRepository commissionRepo;

}