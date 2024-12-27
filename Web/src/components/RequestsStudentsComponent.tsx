import {IconCheck, IconTrash} from '@tabler/icons-react';
import {ActionIcon, Box, Group, Loader, Notification, Table, Text} from '@mantine/core';
import {Link, useParams} from "react-router-dom";
import {IoCloseCircleOutline} from "react-icons/io5";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchAccessStudent} from "../api/service.ts";
import {axiosConfig} from "../../axios.ts";
import {queryClient} from "../api/query-client.ts";

export default function RequestsStudentsComponent() {
    const {id} = useParams();

    const modifyStudent = async ({ action, studentId }: { action: "accept-sub" | "reject-sub"; studentId: number }) => {
        const endpoint = `api/student-sub/${action}?id=${studentId}`;
        return (await axiosConfig.put(endpoint)).data;
    };

    const { mutate } = useMutation(modifyStudent, {
        onSuccess: () => {
            queryClient.invalidateQueries(["students-sub"]); // Invalidate queries to refresh data
        },
    });

    const handleAction = (action: "accept-sub" | "reject-sub", studentId: number) => {
        mutate({ action, studentId });
    };

    const { status, data, error } = useQuery(["students-sub", id], () => fetchAccessStudent(Number(id)));

    if (status === "loading") {
        return (
            <Box style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                <Loader size="lg" />
            </Box>
        );
    } else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        );
    }
    const filteredData = data.data.filter((item) => item.status === "NOT_CONSIDERED") || [];
    if (filteredData.length === 0) {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Text color="dimmed" size="lg">
                    Нет Запросов
                </Text>
            </Box>
        );
    }
    const rows = filteredData.map((item) => (
        <Table.Tr key={item.id}>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.id}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.student}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap={0} justify="flex-end">
                    <ActionIcon onClick={() => handleAction("accept-sub", item.id)} variant="subtle" color="green">
                        <IconCheck size={16} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon onClick={() => handleAction("reject-sub", item.id)} variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <>
            <Box display={"flex"} ml={"98%"}>
                <Link to={`/group-students/${id}`}>
                    <ActionIcon radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32} />
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"}>Запросы Группы</Text>
            <Table.ScrollContainer minWidth={800}>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th>ФИО</Table.Th>
                            <Table.Th>Chat id</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows}</Table.Tbody>
                </Table>
            </Table.ScrollContainer>
        </>
    );
}